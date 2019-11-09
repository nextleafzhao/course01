package com.hzht.course01.controller;

import com.hzht.course01.exception.BusinessErrorException;
import com.hzht.course01.exception.BusinessMsgEnum;
import com.hzht.course01.util.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 黄昭鸿
 * @create 2019-08-01 上午11:01
 */
@RestController
@RequestMapping("/exception")
public class ExceptionController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    /**
     * 测试请求参数缺失
     */
    @PostMapping("/test")
    public JsonResult test(@RequestParam(value = "name") String username,
                           @RequestParam(value = "pass") String password) {
        logger.info("请求参数没有缺失");
        logger.info("姓名：{}", username);
        logger.info("密码：{}", password);
        return new JsonResult();
    }

    @GetMapping("/testNullPointException")
    public JsonResult testNullPointException() {
        String a = null;
        a.equals("a");
        /*正确写法："a".equals(a);*/
        return new JsonResult();
    }

    /**
     * 自定义异常测试
     */
    @GetMapping("/business")
    public JsonResult testMyException() {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            throw new BusinessErrorException(BusinessMsgEnum.UNEXPECTED_EXCEPTION);
        }
        return new JsonResult();
    }
}
