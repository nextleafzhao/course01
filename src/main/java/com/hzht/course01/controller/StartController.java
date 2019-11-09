package com.hzht.course01.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 黄昭鸿
 * @create 2019-07-29 下午1:15
 * RestController注解可返回JSON格式的数据(Jackson框架)
 * 如果是前后端分离,不用模板渲染的话,比如 Thymeleaf,可以直接使用 @RestController
 * 如果不是前后端分离,需要使用模板渲染的话,一般 Controller 中都会返回到具体的页面,
 * 此时就不能使用@RestController了
 */
@RestController
@RequestMapping("/start")
public class StartController {
    @RequestMapping("/springboot")
    public String startSpringBoot(){
        return "Welcome to the world of Spring Boot !";
    }
}
