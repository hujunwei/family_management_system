package com.mashibing.returnJson;

/**
 * @Description: com.mashibing.returnObject
 */
public class ReturnObject {
    private String message;
    private Integer code;
    private Object result;

    public ReturnObject() {
    }

    public ReturnObject(Object result) {
        this.result = result;
    }

    public ReturnObject(String message, Object result) {
        this.message = message;
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
