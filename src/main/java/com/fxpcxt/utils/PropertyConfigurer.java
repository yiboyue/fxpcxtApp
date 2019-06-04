package com.fxpcxt.utils;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.IOException;
import java.util.Properties;

/**
 * 继承 {@link org.springframework.beans.factory.config.PropertyPlaceholderConfigurer PropertyConfigurer}
 * 实现加载properties到自定义的配置对象中
 *
 * @author:
 * @Date: 2018/4/24
 **/
public class PropertyConfigurer extends PropertyPlaceholderConfigurer {

    private static Properties props;

    /**
     * 原方法是从文件中加载合并
     *
     * @return
     * @throws IOException
     */
    @Override
    protected Properties mergeProperties() throws IOException {
        props = super.mergeProperties();

        return props;
    }

    public Properties getProperties() {
        return props;
    }

}
