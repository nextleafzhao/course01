package com.hzht.course01.controller;

import com.hzht.course01.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 黄昭鸿
 * @create 2019-07-29 下午2:03
 */
@RestController
@RequestMapping("/jackson")
public class JacksonController {
    private static final Logger logger = LoggerFactory.getLogger(JacksonController.class);

    @RequestMapping("/user")
    public User getUser() {
        return new User((long) 1, "黄昭鸿", "123456");
    }

    @RequestMapping("/list")
    public List<User> getUserList() {
        List<User> userList = new ArrayList<>(3);
        userList.add(new User((long) 1, "黄昭鸿", "123456"));
        userList.add(new User((long) 2, "黄昭鸿2", "654123"));
        return userList;
    }

    @RequestMapping("/map")
    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>(3);
        map.put("作者信息", new User((long) 1, "黄昭鸿", "123456"));
        map.put("博客地址", "http://blog.itcodai.com");
        map.put("抖音号", null);
        map.put("粉丝数量", "4153");
        return map;
    }

    @RequestMapping("/log")
    public String testLog() {
        logger.debug("这是debug日志");
        logger.info("这是info日志");
        logger.warn("这是warn日志");
        logger.error("这是error日志");
        return "success";
    }
}
