package com.fxpcxt;

import javax.servlet.MultipartConfigElement;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Configuration
@SpringBootApplication
@EnableTransactionManagement
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.fxpcxt"})
@MapperScan({"com.fxpcxt.dao"})
public class FxpcxtApplication {
	public static void main(String[] args) {
        SpringApplication.run(FxpcxtApplication.class, args);
    }
	
	@Bean
	public MultipartConfigElement multipartConfigElement(){
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("102400KB");
		factory.setMaxRequestSize("102400KB");
		return factory.createMultipartConfig();
	}
}
