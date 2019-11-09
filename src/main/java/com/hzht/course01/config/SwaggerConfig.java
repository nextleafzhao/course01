package com.hzht.course01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 黄昭鸿
 * @create 2019-07-30 下午11:56
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                /*指定构建 API 文档的详细信息的方法：apiInfo()*/
                .apiInfo(apiInfo())
                .select()
                /*指定要生成 API 接口的包路径，这里把 Controller 作为包路径，生成 Controller 中的所有接口*/
                .apis(RequestHandlerSelectors.basePackage("com.hzht.course01.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 构建 API 文档的详细信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("这是个标题哦")
                .description("跟武哥一起学Spring Boot第06课")
                .contact(new Contact("黄昭鸿", "http://blog.csdn.net/eson_15", "nextleaf@qq.com"))
                .version("1.0")
                .build();
    }
}
