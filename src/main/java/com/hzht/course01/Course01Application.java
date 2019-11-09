package com.hzht.course01;

import com.hzht.course01.entity.MicroServiceUrl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * 在 Spring Boot 启动类上添加 @MaperScan 注解，用来扫描某个包下的所有 mapper。
 * @author 黄昭鸿
 */
@SpringBootApplication
@MapperScan("com.hzht.course01.dao")
public class Course01Application {
    @Resource
    private MicroServiceUrl microServiceUrl;

    public static void main(String[] args) {
        SpringApplication.run(Course01Application.class, args);
    }

    /**
     * 解决ajax跨域问题
     * 跨域是指html文件所在的服务器与ajax请求的服务器是不同的ip+port
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(microServiceUrl.getCorsUrl());
            }
        };
    }

}
