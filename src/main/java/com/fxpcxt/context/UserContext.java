package com.fxpcxt.context;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.fxpcxt.common.UserStatus;
import com.fxpcxt.entity.User;
import com.fxpcxt.jwt.utils.JwtTokenUtil;
import com.fxpcxt.jwt.utils.NoToken;
import com.fxpcxt.utils.PropertyReader;
/**
 * 用户上下文
 * @author lujianfen
 *
 */
public class UserContext implements CoreContext{
	
	/**
     * Authorization
     */
    static final String AUTHORIZATION_HEADER_NAME = "Token";
    
    /**
     * 权限数据长度
     */
    static final int AUTHORIZATION_LENGTH = 2;
	
	private HttpServletRequest request;
	
	private String token;
	
	private UserStatus status;
	
	private User user;
	
	public UserContext(HttpServletRequest request) {
		this.request = request;
		String[] authorization = getAuthorization(request);
	    this.token = authorization[1];
	    if(StringUtils.hasText(token)){
	          this.user = JwtTokenUtil.getInstance().getTokenUserFromToken(token);
	    }
	}
	
	public UserContext(HttpServletRequest request, NoToken noToken){
		this.request = request;
		if(noToken != null ){
			this.user = new User();
		}else{
			String[] authorization = getAuthorization(request);
			this.token = authorization[1];
			if(StringUtils.hasText(token)){
			     this.user = JwtTokenUtil.getInstance().getTokenUserFromToken(token);
			}
		}
	}
	
	 /**
     * 获取AUTHORIZATION_HEADER对应的header内容(以空格分割的两部分)
     * @param request
     * @return
     */
    static String[] getAuthorization(HttpServletRequest request){
    	Assert.notNull(request,"request must be not null!");
        String auth = request.getHeader(AUTHORIZATION_HEADER_NAME);
        //要求客户端发送身份认证信息,并且只能是Bearer认证方式中
        String[] authorization = new String[AUTHORIZATION_LENGTH];
        authorization[0] = "";
        authorization[1] = "";
        if(StringUtils.hasText(auth)) {
            String[] splitKeys = auth.split("\\s+");
            if(splitKeys.length == AUTHORIZATION_LENGTH) {
                authorization[0] = splitKeys[0].trim();
                authorization[1] = splitKeys[1].trim();
            }
        }
        return authorization;
    }
    
	@Override
	public User getUser() {
		return JwtTokenUtil.getInstance().getTokenUserFromToken(token);
	}

	@Override
	public String getToken() {
		return token;
	}

	@Override
	public UserStatus getStatus() {
		if(status!=null){
	        return status;
	    }
	    status = UserStatus.OFFLINE;
	    User user = getUser();
	    if(user!=null) {
	        status = UserStatus.ONLINE;
	    }
	    return status;
	}

}
