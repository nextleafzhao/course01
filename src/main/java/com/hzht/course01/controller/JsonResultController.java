package com.hzht.course01.controller;

import com.hzht.course01.entity.User;
import com.hzht.course01.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 黄昭鸿
 * @create 2019-07-29 下午2:53
 */
@RestController
@RequestMapping("/jsonresult")
public class JsonResultController {
    private static final Logger logger = LoggerFactory.getLogger(JsonResultController.class);

    @RequestMapping("/user")
    public JsonResult<User> getUser() {
        User user = new User((long) 1, "黄昭鸿", "1234566");
        return new JsonResult<>(user);
    }

    @RequestMapping("/list")
    public JsonResult<List> getUserList() {
        List<User> userList = new ArrayList<>();
        userList.add(new User((long) 1, "黄昭鸿", "1234566"));
        userList.add(new User((long) 2, "黄昭鸿2", "4566123"));
        return new JsonResult<>(userList, "获取用户列表成功");
    }

    @RequestMapping("/map")
    public JsonResult<Map> getUserMap() {
        Map<String, Object> userMap = new HashMap<>(3);
        userMap.put("作者信息", new User((long) 1, "黄昭鸿", "1234566"));
        userMap.put("博客地址", "http://blog.itcodai.com");
        userMap.put("抖音号", null);
        userMap.put("粉丝数量", "4153");
        return new JsonResult<>(userMap);
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
