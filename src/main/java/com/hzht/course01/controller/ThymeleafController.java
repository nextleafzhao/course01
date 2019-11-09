package com.hzht.course01.controller;

import com.hzht.course01.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 黄昭鸿
 * @create 2019-07-31 下午11:32
 * @RestController 注解，自动会把返回的数据转成 JSON 格式。
 * 但是在使用模板引擎时，Controller 层就不能用 @RestController 注解了，
 * 因为在使用 Thymeleaf 模板时，返回的是视图文件名，
 * 比如上面的 Controller，是返回到 index.html 页面，
 * 如果使用 @RestController 的话，会把 index 当作 String 解析了，直接返回到页面了
 */
@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {
    @RequestMapping("/test404")
    public String test404() {
        return "index";
        /*return "马🏇吗";*/
    }

    @RequestMapping("/test500")
    public String test500() {
        int i = 1 / 0;
        return "index";
    }

    @RequestMapping("/getUser")
    public String getUser(Model model) {
        User user = new User(1L, "黄昭鸿", "123654");
        model.addAttribute("user", user);
        return "user";
    }

    @RequestMapping("/getList")
    public String getList(Model model) {
        User user1 = new User(1L, "黄昭鸿", "123654");
        User user2 = new User(2L, "倪升武", "123456");
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        model.addAttribute("users", users);
        return "list";
    }

}
