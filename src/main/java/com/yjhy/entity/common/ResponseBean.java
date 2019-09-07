package com.yjhy.entity.common;

import java.io.Serializable;

/**
 * Created by husong on 2018/10/18.
 */
public class ResponseBean<T> implements Serializable {

    private Integer code;
    private String error;
    private T data;

    public ResponseBean() {
        this.code = StatusCodes.SUCCESS;
        this.error = "";
        this.data = null;
    }

    public void setSuccess() {
        this.code = StatusCodes.SUCCESS;
        this.error = "";
    }

    public void setError(String error) {
        this.code = StatusCodes.ERROR;
        this.error = error;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
