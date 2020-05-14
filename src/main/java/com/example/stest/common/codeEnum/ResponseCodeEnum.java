package com.example.stest.common.codeEnum;

/**
 * 错误代码枚举类
 *
 */
public enum ResponseCodeEnum {
 
    SUCCESS("200", "success"),
    
    CREATED("201", "新增实体已存在"),
    
    ACCEPTED("202", "服务器已接受请求，但未处理"),

	WAIT("203", "请求资源过于频繁，请稍后再试"),

    UNAUTHORIZED("401", "未授权"),
    
    FOBIDDEN("403", "访问拒绝"),
    
    NOT_FOUND("404", "指定的资源不存在"),
    
    SERVICE_ERROR("500", "服务端错误"),
 
    UNKNOWN_ERROR("9999", "系统繁忙，请稍后再试....");
 
	private String code;
	
	private String message;
	
	ResponseCodeEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}
 
	public String getCode() {
		return this.code;
	}
 
 
	public String getMessage() {
		return message;
	}
 
	@Override
	public String toString() {
		return "[" + this.code + "]" + this.message;
	}
}

