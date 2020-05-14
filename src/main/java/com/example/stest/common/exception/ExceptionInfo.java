package com.example.stest.common.exception;

/**
 * ExceptionInfo
 * @date 2018.11.8
 * @author ccx
 */
public class ExceptionInfo<T> {
	public static final Integer OK = 0;
    public static final Integer ERROR = 100;

    private String code;
    private String message;
    private String url;
    private T data;
    
    public ExceptionInfo() {
    	
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
