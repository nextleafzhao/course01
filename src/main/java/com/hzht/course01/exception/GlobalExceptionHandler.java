package com.hzht.course01.exception;

import com.hzht.course01.util.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author 黄昭鸿
 * @create 2019-08-01 上午10:42
 * 使用 Spring Boot 拦截并处理全局的异常
 * 注解 @ResponseBody 是为了异常处理完之后给调用方输出一个 JSON 格式的封装数据。
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /*处理参数缺失异常*/
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public JsonResult handleHttpMessageNotReadableException(MissingServletRequestParameterException ex) {
        logger.error("请求参数缺失：{}", ex.getMessage());
        return new JsonResult("400", "缺少必要参数");
    }

    /**
     * 处理空指针异常
     *
     * @param ex NullPointerException
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult handleTypeMismatchException(NullPointerException ex) {
        logger.error("空指针异常：{}", ex.getMessage());
        return new JsonResult("500", "空指针异常了呀");
    }

    @ExceptionHandler(BusinessErrorException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult handleBusinessError(BusinessErrorException bex) {
        String code = bex.getCode();
        String message = bex.getMessage();
        return new JsonResult(code, message);
    }

    /**
     * 系统异常 预期以外异常
     * Exception 异常是父类，所有异常都会继承该异常
     * 拦截 Exception 虽然可以一劳永逸，但是不利于我们去排查或者定位问题。
     * 实际项目中，可以把拦截 Exception 异常写在 GlobalExceptionHandler 最下面
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult handleUnexpectedServer(Exception e) {
        logger.error("系统异常：", e);
        return new JsonResult("500", "系统发生异常，请联系管理员");
    }
}
