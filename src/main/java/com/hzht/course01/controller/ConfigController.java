package com.hzht.course01.controller;

import com.hzht.course01.entity.MicroServiceUrl;
import com.hzht.course01.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 黄昭鸿
 * @create 2019-07-29 下午5:37
 */
@RestController
@RequestMapping("/test")
public class ConfigController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigController.class);

    @Value("${url.orderUrl}")
    private String orderUrl;

    @Resource
    private MicroServiceUrl microServiceUrl;

    @RequestMapping("/config")
    public String testConfig() {
        LOGGER.info("订单服务地址为：{}", orderUrl);
        LOGGER.info("=====获取的订单服务地址为:{}", microServiceUrl.getOrderUrl());
        LOGGER.info("=====获取的用户服务地址为:{}", microServiceUrl.getUserUrl());
        LOGGER.info("=====获取的购物车服务地址为:{}", microServiceUrl.getShoppingUrl());
        return "success";
    }

    @GetMapping("/pathv/{idd}/{name}")
    public String testPathVariable(@PathVariable(value = "idd") Integer id, @PathVariable String name) {
        /**
         * 注解为@RequestMapping("/pathv/{idd}/{name}")也可以
         * */
        LOGGER.info("请求的ID为：" + id);
        LOGGER.info("请求的名为：" + name);
        return "success";
    }

    @GetMapping("/param")
    public String testRequestParam(@RequestParam(defaultValue = "0") Integer id, @RequestParam(value = "uname", required = false) String name) {
        /**
         * @RequestParam注解适应问号风格(http://localhost:8001/test/param?id=666&uname=huang)的请求
         * 该注解还可以用于 POST 请求
         * */
        LOGGER.info("请求的ID为：" + id);
        if (name != null) {
            if (!"".equals(name)) {
                LOGGER.info("请求的名为：" + name);
            } else {
                LOGGER.info("请求的名为空值");
            }
        }

        return "successs";
    }

    @PostMapping("/form1")
    public String testRequestParam2(@RequestParam String username, @RequestParam String password) {
        /**
         * @RequestParam注解还可以用于 POST 请求
         * 请求主体示例：username=huangg&password=49644161
         * */

        LOGGER.info("表单username为：" + username);
        LOGGER.info("表单password为：" + password);
        return "success";
    }

    @PostMapping("/form2")
    public String testRequestParam3(User user) {
        /**
         * 如果表单数据很多，使用实体接收，且不必在前面加 @RequestParam 注解
         * 实体中的属性名和表单中的参数名一致即可
         * */
        LOGGER.info("User ID为：" + user.getId());
        LOGGER.info("User username为：" + user.getUsername());
        LOGGER.info("User password为：" + user.getPassword());
        return "success";
    }

    @PostMapping("/postjson")
    public String testJSONSubmit(@RequestBody User user) {
        /**
         * @RequestBody 注解用于 POST 请求上,接收 JSON 实体参数,
         * 比如前端通过JSON提交参数,此时我们需要在后端封装一个实体来接收。
         * 在传递的参数比较多的情况下,使用 @RequestBody 接收会非常方便。
         * */
        LOGGER.info("JSON提交的User ID为：" + user.getId());
        LOGGER.info("JSON提交的User username为：" + user.getUsername());
        LOGGER.info("JSON提交的User password为：" + user.getPassword());
        return "success";
    }

}
