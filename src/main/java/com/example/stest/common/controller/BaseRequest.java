package com.example.stest.common.controller;

import javax.validation.Valid;
import java.io.Serializable;

public class BaseRequest<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sessionId;
    private String from = "web";
    @Valid
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
