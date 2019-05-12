package com.fxpcxt.jwt.utils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.fxpcxt.context.AppContext;
import com.fxpcxt.entity.User;
import com.fxpcxt.utils.PropertyReader;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.DefaultClock;
import io.jsonwebtoken.impl.TextCodec;
import io.jsonwebtoken.lang.Assert;

/**
 * @author 
 * @version 创建时间：2018/4/28 15:01
 */
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -4531618951410445159L;

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    /**
     * 算法
     */
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM;

    /**
     * JWT_SECRET
     * @Value("${jwt.secret:udinghuo-hh9PWjfI-20150820}")
     */
    private static final String JWT_SECRET;

    /**
     * JWT_EXPIRATION(过期时间间隔)
     * @Value("${jwt.expiration:1800}")
     */
    private static final Long JWT_EXPIRATION;
    
    /**
     * 过期时间间隔最大值为12小时
     */
    private static final Long JWT_EXPIRATION_MAX = 43200L;

    /**
     * 过期时间间隔最小值为30分钟
     */
    private static final Long JWT_EXPIRATION_MIN = 1800L;

    static{
        SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
        JWT_SECRET = "fxpcxtapp-hh9PWjfI-20150820";
        //过期时间间隔默认为一小时
        JWT_EXPIRATION = 3600L;
    }

    private static JwtTokenUtil ourInstance = new JwtTokenUtil();

    public static JwtTokenUtil getInstance() {
        return ourInstance;
    }

    /**
     * base64Secret
     */
    private String base64Secret;

    private JwtTokenUtil() {
        base64Secret = TextCodec.BASE64.encode(JWT_SECRET);
    }

    private Clock clock = DefaultClock.INSTANCE;

    /**
     * 获取jwtBuilder
     * @return
     */
    private JwtBuilder getJwtBuilder(){
        return Jwts.builder();
    }

    /**
     * 获取jwtPareser
     * @return
     */
    private JwtParser getJwtParser(){
        return Jwts.parser();
    }

    /**
     * 获取TokenDetail
     * @param token
     * @return
     */
    public User getTokenUserFromToken(String token) {
        if(!StringUtils.hasText(token)){
            return new User();
        }
        final Claims claims = getAllClaimsFromToken(token);
        final String result = claims.getSubject();
        final User user = AppContext.fromJson(result, User.class);
        return user;
    }



    /**
     * jwt的subject
     * @param token
     * @return
     */
    public String getSubjectFromToken(String token){
        return getClaimFromToken(token,Claims::getSubject);
    }

    /**
     * jwt的签发时间
     * @param token
     * @return
     */
    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    /**
     * jwt的过期时间，这个过期时间必须要大于签发时间
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * 从token获取信息
     * @param token
     * @param claimsResolver
     * @param <T>
     * @return
     */
    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 从token解密
     * @param token
     * @return
     */
    private Claims getAllClaimsFromToken(String token) {
        if (!StringUtils.hasText(token)) {
            throw new RuntimeException(String.format("缺少token信息，请重新登录!",token));
        }

        try {
            final Claims claims = getJwtParser()
                    .setSigningKey(base64Secret)
                    .parseClaimsJws(token)
                    .getBody();
            if(claims == null || claims.isEmpty()){
                throw new RuntimeException(String.format("token[%s]无效，请重新登录!",token));
            }
            return claims;
        }catch (ExpiredJwtException e) {
            LOGGER.warn(String.format("JwtTokenUtil.getTokenUserFromToken.expiredToken:(%s,%s)", e.getMessage(),token));
            throw new RuntimeException("token已过期,请重新登录!");
        }catch (SignatureException e){
            LOGGER.warn(String.format("JwtTokenUtil.getTokenUserFromToken.SignatureException:%s",token));
            throw new RuntimeException(String.format("非法的token信息[%s],请重新登录!",token));
        }
    }

    /**
     * generate jwt token
     * @param tokenDetail
     * @return
     */
    public String generateToken(User tokenDetail) {
        Map<String, Object> claims = new HashMap<>(16);
        return doGenerateToken(claims, tokenDetail.toString());
    }

    /**
     * 根据条件生成token
     * @param claims
     * @param subject
     * @return
     */
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        final Date createdDate = new Date(clock.now().getTime()-30*1000);
        final Date expirationDate = calculateExpirationDate(createdDate);
        /**
         * 可以在claims里设置，也可以通过JwtBuilder设置，效果相同
         * iss: jwt签发者
         * sub: jwt所面向的用户
         * aud: 接收jwt的一方
         * exp: jwt的过期时间，这个过期时间必须要大于签发时间
         * nbf: 定义在什么时间之前，该jwt都是不可用的.
         * iat: jwt的签发时间
         * jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
         */
        return getJwtBuilder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .setId(IDUtil.shortUuid())
                .setNotBefore(createdDate)
                .signWith(SIGNATURE_ALGORITHM, base64Secret)
                .compact();
    }

    /**
     * 计算过期时间
     * @param createdDate
     * @return
     */
    private Date calculateExpirationDate(Date createdDate) {
        Assert.notNull(createdDate,"createDate must be not null!");
        if(JWT_EXPIRATION<JWT_EXPIRATION_MIN || JWT_EXPIRATION>JWT_EXPIRATION_MAX){
            throw new RuntimeException("过期时间设置不合理!");
        }
        long expiration = JWT_EXPIRATION;
        return new Date(createdDate.getTime() + expiration * 1000);
    }
}

