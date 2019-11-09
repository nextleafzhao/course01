package com.hzht.course01.exception;

/**
 * @author 黄昭鸿
 * 自定义业务异常
 * @create 2019-08-01 下午12:22
 */
public class BusinessErrorException extends RuntimeException {
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    /**
     * 在构造方法中，传入我们上面自定义的异常枚举类，
     * 在项目中，如果有新的异常信息需要添加，我们直接在枚举类中添加即可
     */
    public BusinessErrorException(BusinessMsgEnum businessMsgEnum) {
        this.code = businessMsgEnum.getCode();
        this.message = businessMsgEnum.getMsg();
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
