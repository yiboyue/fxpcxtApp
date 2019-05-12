package com.fxpcxt.context;

import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author
 */
public class AppContext implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	private static ThreadLocal<CoreContext> USER_CTX = new ThreadLocal<>();

	private static final String JACKSON_BEAN_NAME = "jacksonObjectMapper";

	private static ObjectMapper objectMapper;

	/**
	 * 获取上下文
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 设置上下文
	 * @param applicationContext
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		AppContext.applicationContext = applicationContext;
		objectMapper = (ObjectMapper)getBean(JACKSON_BEAN_NAME);
	}

	/**
	 * jackson ObjectMapper
	 * @return
	 */
	private static ObjectMapper getObjectMapper(){
		if(objectMapper==null){
			objectMapper = new ObjectMapper();
			objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		}
		return objectMapper;
	}

	/**
	 * convert Json Object to String
	 * 默认是否输出pretty的json取决于配置文件
	 * @param jsonObject
	 * @return
	 */
	public static String toJson(Serializable jsonObject){
		return toMinimalPrettyJson(jsonObject);
	}

	/**
	 * convert Json Object to String
	 * 强制输出无格式json数据
	 * @param jsonObject
	 * @return
	 */
	public static String toMinimalPrettyJson(Serializable jsonObject){
		try{
			ObjectMapper mapper = getObjectMapper();
			return mapper.writer((PrettyPrinter)null).writeValueAsString(jsonObject);
		}catch (JsonProcessingException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 输出可视化的json数据
	 * @param jsonObject
	 * @return
	 */
	public static String toPrettyJson(Serializable jsonObject){
		try{
			ObjectMapper mapper = getObjectMapper();
			return mapper.writeValueAsString(jsonObject);
		}catch (JsonProcessingException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 转换json字符串为对象
	 * @param jsonString
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> T fromJson(String jsonString,Class<T> clazz){
		try {
			ObjectMapper mapper = getObjectMapper();
			return mapper.readValue(jsonString, clazz);
		}catch (JsonMappingException e){
			throw new RuntimeException(e);
		}catch (JsonParseException e){
			throw new RuntimeException(e);
		}catch (IOException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 转换json字符串为对象
	 * @param jsonString
	 * @param valueTypeRef
	 * @param <T>
	 * @return
	 */
	public static <T> T fromJson(String jsonString,TypeReference valueTypeRef){
		try {
			ObjectMapper mapper = getObjectMapper();
			return mapper.readValue(jsonString, valueTypeRef);
		}catch (JsonMappingException e){
			throw new RuntimeException(e);
		}catch (JsonParseException e){
			throw new RuntimeException(e);
		}catch (IOException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 通过名字获取上下文中的bean
	 * @param name
	 * @return
	 */
	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	/**
	 * 根据类型和Bean id获取对象实例。如果找不到该类型的实例则返回null。
	 * @param <T>      类型参数
	 * @param beanName 实现类在容器中配置的名字
	 * @param beanType 实例的类型
	 * @return 指定类型的实例。
	 */
	public static <T> T getBean(Class<T> beanType,String beanName){
		try {
			return applicationContext.getBean(beanName,beanType);
		} catch (NoUniqueBeanDefinitionException e) {
			throw new RuntimeException(e);
		} catch (NoSuchBeanDefinitionException e) {
			return null;
		}
	}

	/**
	 * 通过名字获取上下文中的bean
	 * @param name
	 * @return
	 */
	public static boolean containBean(String name) {
		return applicationContext.containsBean(name);
	}

	/**
	 * 通过类型获取上下文中的bean
	 * @param beanType
	 * @param <T>
	 * @return
	 */
	public static <T> T getBean(Class<T> beanType) {
		try {
			return applicationContext.getBean(beanType);
		} catch (NoUniqueBeanDefinitionException e) {
			throw new RuntimeException(e);
		} catch (NoSuchBeanDefinitionException e) {
			return null;
		}
	}

	/**
	 * 通过类型获取所有bean
	 * @param beanType
	 * @param <T>
	 * @return
	 */
	public static <T> Set<T> getBeans(Class<T> beanType) {
		return new HashSet<>(applicationContext.getBeansOfType(beanType).values());
	}

	/**
	 * 根据类型和Annotation获取对象实例。如果找不到该类型的实例则返回null。
	 * 假如有两个类MyService1和MyService2都实现了接口Service，其中MyService2标记为
	 * TheAnnotation，那么getInstance(Service.class, TheAnnotation.class)将返回
	 * MyService2的实例。
	 *
	 * @param <T>            类型参数
	 * @param beanType       实例的类型
	 * @param annotationType 实现类的annotation类型
	 * @return 指定类型的实例。
	 */
	public static <T> T getBeans(Class<T> beanType, Class<? extends Annotation> annotationType) {
		if (annotationType == null) {
			return getBean(beanType);
		}
		Map<String, T> results = applicationContext.getBeansOfType(beanType);
		List<T> resultsWithAnnotation = new ArrayList<T>();
		for (Map.Entry<String, T> entry : results.entrySet()) {
			if (applicationContext.findAnnotationOnBean(entry.getKey(), annotationType) != null) {
				resultsWithAnnotation.add(entry.getValue());
			}
		}
		if (resultsWithAnnotation.isEmpty()) {
			return null;
		}
		if (resultsWithAnnotation.size() == 1) {
			return resultsWithAnnotation.get(0);
		}
		throw new RuntimeException();
	}

	/**
	 * 添加用户上下文
	 * @param context
	 */
	public static void setBizContext(CoreContext context) {
		USER_CTX.set(context);
	}

	/**
	 * 获取用户上下文
	 * @return
	 */
	public static CoreContext getBizContext() {
		return USER_CTX.get();
	}

	/**
	 * 防止内存泄漏
	 */
	public static void removeBizContext(){
		USER_CTX.remove();
	}
}

