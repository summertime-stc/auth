package com.example.stest.common.controller;

import lombok.Data;
import lombok.ToString;

import java.util.Collection;

@Data
public class ResultBase<T> {
    private String code;
    private String message;
    private T data;

    private ResultBase() {

    }

    public static ResultBase error(String code, String message) {
        ResultBase resultBean = new ResultBase();
        resultBean.setCode(code);
        resultBean.setMessage(message);
        return resultBean;
    }

    public static ResultBase success() {
        ResultBase resultBean = new ResultBase();
        resultBean.setCode("0");
        resultBean.setMessage("success");
        return resultBean;
    }

    public static <V> ResultBase<V> success(Object data) {
        ResultBase resultBean = new ResultBase();
        resultBean.setCode("0");
        resultBean.setMessage("success");
        resultBean.setData(data);
        return resultBean;
    }

    // getter / setter 略
}