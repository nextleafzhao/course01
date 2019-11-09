package com.hzht.course01.controller;

import com.hzht.course01.entity.User;
import com.hzht.course01.service.UserService;
import com.hzht.course01.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 黄昭鸿
 * @create 2019-07-31 上午1:47
 * 注解@Api表示标识的这个类是Swagger2的资源
 * 注解@ApiOperation用于方法上，表示一个HTTP 请求操作
 * 测试几个接口
 */
@RestController
@RequestMapping("/swagger")
@Api(value = "Swagger2在线接口文档")
public class TestController {
    @Resource
    private UserService userService;

    @RequestMapping("/getUserByName/{name}")
    public JsonResult<User> getUserByName(@PathVariable String name) {
        return new JsonResult<>(userService.getUserByName(name));
    }

    /**
     * 按ID查询
     *
     * @param id
     * @return
     */
    @RequestMapping("/getUser/{id}")
    public JsonResult<User> getUser(@PathVariable Long id) {
        return new JsonResult<>(userService.getUser(id));
    }

    @GetMapping("/get/{id}")
    @ApiOperation(value = "根据用户唯一标识获取用户信息啊啊啊")
    public JsonResult<User> getUserInfo(@PathVariable @ApiParam(value = "用户的唯一标识") Long id) {
        /*模拟在数据库中根据id获取User信息*/
        User user = new User(id, "黄昭鸿", "123456789");
        return new JsonResult<>(user);
    }

    @PostMapping("/insert")
    @ApiOperation(value = "添加用户信息")
    public JsonResult<Void> insertUser(@RequestBody @ApiParam(value = "用户信息") User user) {
        /* TODO 处理添加用户信息逻辑*/
        return new JsonResult<>();
    }

    @PostMapping("/adduser")
    public String addUser(@RequestBody User user) {
        if (user != null) {
            userService.insertUser(user);
            return "success";
        } else {
            return "false";
        }
    }
}
