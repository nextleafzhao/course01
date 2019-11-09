package com.hzht.course01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 黄昭鸿
 * AOP
 * @create 2019-08-01 下午6:48
 */
@RestController
@RequestMapping("/aop")
public class AopController {
    @GetMapping("/{name}")
    public String testAop(@PathVariable String name) {
        return "Hello " + name;
    }

}
