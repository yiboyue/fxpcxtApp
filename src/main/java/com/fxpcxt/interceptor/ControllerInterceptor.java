package com.fxpcxt.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fxpcxt.context.AppContext;
import com.fxpcxt.context.CoreContext;
import com.fxpcxt.context.UserContext;
import com.fxpcxt.jwt.utils.NoToken;


/**
 * 
 * @author controller拦截器
 */
@Component
@Aspect
@Order(0)
public class ControllerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);

	/**
	 * 定义拦截规则：拦截com.fxpcxt.controller包下面的所有类中，有@RequestMapping注解的方法。
	 */
	@Pointcut("execution(* com.fxpcxt.controller..*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void controllerMethodPointcut() {
	}

	/**
	 * 记录HTTP请求开始时的日志,初始化上下文
	 */
	@Before("controllerMethodPointcut()")
	public void doBefore(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		HttpServletResponse response = attributes.getResponse();
		initContext(request,response,joinPoint);

	}

	/**
	 * HTTP请求完成时处理
	 * @param joinPoint
	 */
	@After("controllerMethodPointcut()")
	public void doAfter(JoinPoint joinPoint){
		AppContext.removeBizContext();
	}

	/**
	 * 初始化
	 * 
	 * @param request
	 */
	private void initContext(HttpServletRequest request,HttpServletResponse response,JoinPoint joinPoint) {
		String url = request.getRequestURL().toString();
		String queryString = request.getQueryString();
		String methodString = request.getMethod();
		Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		NoToken noToken = method.getAnnotation(NoToken.class);
//		CoreContext context = new UserContext(request, noToken);
//		AppContext.setBizContext(context);
//		if(context!=null) {
//			logger.debug("请求开始, url: {}, method: {}, detail: {}, query: {}", url, methodString, context.getUser().toString(), queryString);
//		}else{
//			logger.error("请求开始, url: {}, method: {}, query: {}", url, methodString, queryString);
//		}
	}
}
