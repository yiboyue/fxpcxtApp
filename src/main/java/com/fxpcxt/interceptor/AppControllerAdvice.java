package com.fxpcxt.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fxpcxt.common.CommonResponse;
import com.fxpcxt.utils.ResponseUtil;

/**
 * 将所有返回值进行包装，包装成一个CommonResponse对象
 * code = 200请求成功
 * code = 999请求失败
 * 返回的数据都在data中
 * 错误信息在message中
 * @author 1917满眼绿意
 *
 */
@RestControllerAdvice
public class AppControllerAdvice implements ResponseBodyAdvice {

	protected Logger logger = LoggerFactory.getLogger(AppControllerAdvice.class);

	/**
	 * 所有异常报错
	 * 
	 * @param request
	 * @param ex
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = Exception.class)
	public Object exceptionHandler(HttpServletRequest request, Exception ex) {
		logger.debug(String.format("url:%s[%s]",request.getRequestURI(),request.getMethod()),ex);
		StringBuilder sbr = new StringBuilder();
		sbr.append(request.getRequestURI())
			.append("-")
			.append(request.getMethod());
		if(ex instanceof NullPointerException){
			logger.warn(String.format("NullPointerException:%s",sbr.toString()),ex);
		}else if(ex instanceof IllegalArgumentException) {
			logger.warn(String.format("message:%s,url:%s,parameter:%s",ex.getMessage(),request.getRequestURI(),request.getQueryString()));
		}else if(ex instanceof NonTransientDataAccessException){
			if(null != ((NonTransientDataAccessException) ex).getRootCause()){
				ex = new Exception(((NonTransientDataAccessException) ex).getRootCause().getMessage());
			}
			logger.warn(String.format("message:%s,detail:%s,parameter:%s",ex.getMessage(),sbr.toString(),request.getQueryString()));
		}else if(ex instanceof HttpMessageNotReadableException) {
			ex = new Exception("无效的请求参数!");
		}else{
			logger.warn(String.format("message:%s,detail:%s,parameter:%s",ex.getMessage(),sbr.toString(),request.getQueryString()));
		}
		return ResponseUtil.error(ex);
	}

	@Override
	public boolean supports(MethodParameter returnType, Class converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType,
			ServerHttpRequest request, ServerHttpResponse response) {
		if (body instanceof CommonResponse) {
			return body;
		}
		return ResponseUtil.success(body);
	}
}
