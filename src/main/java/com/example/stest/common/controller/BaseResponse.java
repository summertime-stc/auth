package com.example.stest.common.controller;

import com.example.stest.common.codeEnum.ServiceExceptionCodeEnum;

import java.beans.Transient;
import java.io.Serializable;


public class BaseResponse<T> implements Serializable {
    
	private static final long serialVersionUID = 1L;
	private String sessionId;
    private String code = ServiceExceptionCodeEnum.SUCCESS.getCode();
    private String message = ServiceExceptionCodeEnum.SUCCESS.getMessage();
    private T data;
    
    public BaseResponse() {
    	
    }
    
    public BaseResponse(T data) {
    	this.data = data;
    }
    
    public BaseResponse(String code, String message) {
    	this.code = code;
    	this.message = message;
    }

    public BaseResponse(String code, String message, String sessionId) {
    	this.code = code;
    	this.message = message;
        this.sessionId = sessionId;
    }
    
    @Transient
    public boolean isValidate() {
    	return code == ServiceExceptionCodeEnum.SUCCESS.getCode() ? true : false;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
    
}
