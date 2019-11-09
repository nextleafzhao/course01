package com.hzht.course01.util;

/**
 * @author 黄昭鸿
 * @create 2019-07-29 下午2:50
 * 统一的 JSON 结构中属性包括数据、状态码、提示信息即可，构造方法可以根据实际业务需求做相应的添加。
 * 一般来说，应该有默认的返回结构，也应该有用户指定的返回结构
 */
public class JsonResult<T> {
    private T data;
    /*异常码*/
    private String code;

    /*异常信息*/
    private String msg;

    /**
     * 若没有数据返回，默认状态码为 200，提示信息为“操作成功！”
     */
    public JsonResult() {
        this.code = "200";
        this.msg = "操作成功！";
    }

    /**
     * 若没有数据返回，可以人为指定状态码和提示信息
     *
     * @param code
     * @param msg
     */
    public JsonResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 有数据返回时，状态码为 200，默认提示信息为“操作成功！”
     *
     * @param data
     */
    public JsonResult(T data) {
        this.data = data;
        this.code = "200";
        this.msg = "操作成功！";
    }

    /**
     * 有数据返回，状态码为 200，人为指定提示信息
     *
     * @param data
     * @param msg
     */
    public JsonResult(T data, String msg) {
        this.data = data;
        this.code = "200";
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
