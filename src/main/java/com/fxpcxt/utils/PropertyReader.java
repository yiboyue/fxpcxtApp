package com.fxpcxt.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 
 * @version 创建时间：2018/5/1 01:22
 */
public class PropertyReader {

	protected static final Logger LOGGER = LoggerFactory.getLogger(PropertyReader.class);

	private static PropertyReader ourInstance = new PropertyReader();

	public static PropertyReader getInstance() {
		return ourInstance;
	}

	private PropertyConfigurer configurer;

    private PropertyReader() {
        configurer = new PropertyConfigurer();
    }

	/**
	 * 获取属性
	 * 
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
		return configurer.getProperties().getProperty(key);
	}

	/**
	 * 获取带默认值的属性
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public String getProperty(String key, String defaultValue) {
		return configurer.getProperties().getProperty(key, defaultValue);
	}

	/**
	 * 是否包含该属性
	 * 
	 * @param key
	 * @return
	 */
	public boolean containsKey(String key) {
		return configurer.getProperties().containsKey(key);
	}

}
