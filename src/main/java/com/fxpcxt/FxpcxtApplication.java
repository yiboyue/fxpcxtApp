package com.fxpcxt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.fxpcxt"})
@MapperScan({"com.fxpcxt.dao"})
public class FxpcxtApplication {
	public static void main(String[] args) {
        SpringApplication.run(FxpcxtApplication.class, args);
    }
}
