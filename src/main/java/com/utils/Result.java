package com.utils;
import lombok.Data;

@Data
public class Result {
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    // 数据返回统一格式
    private Object data;
    //返回状态码
    private Integer code;
    //返回描述信息
    private String message;
    // token ---UUID
    private String uuid;
    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public Result(Integer code, Object data, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }
    public Result(Integer code, Object data, String message, String uuid) {
        this.data = data;
        this.code = code;
        this.message = message;
        this.uuid = uuid;
    }
}
