package com.example.stest.common.codeEnum;

/**
 * 错误代码枚举类
 *
 */
public enum ServiceExceptionCodeEnum {
 
    SUCCESS("0000", "success"),
    
    PARAM_EMPTY("1001", "必选参数为空"),
 
    PARAM_ERROR("1002", "参数格式错误"),

	TIMESTAMP("1003","时间戳不能为空"),

	ILLEGALTIMESTAMP("1004","时间戳入参不合法"),
    
    DELETE_NULLPOINT("1005", "需要删除的对象不存在"),
    
    CODE_EXISTS("1006", "编码已存在"),

	REQUESTTIMEOUT("1007", "请求已过期"),

	APPIDNOTNULL("1008", "appid授权号不能为空"),

	ILLEGALAPPID("1009", "appid入参不合法"),
 
    SIGNVALIDATIONERROR("1010", "sign认证错误"),

	RESUBMITREQUEST("1011", "请求重复"),

	PERMISSIONDENIED("1012", "无该接口访问权限"),

	FREQUENTREQUESTS("1013", "请求过于频繁！！请稍后再试"),

	UNKNOWN_ERROR("9999", "系统繁忙，请稍后再试...."),
	
	FIRST_DELETE_DIC("2001", "删除分类要先清空字典项"),
	
	FIRST_DISABLED_CHILD("2002", "请先停用下级节点"),
	
	DELETE_FALSE_CAUSE_CHILD("2003", "当前节点存在子节点，无法删除"),
	
	TYPE_CODE_EXISTS("2004", "分类代码已存在"),
	
	DIC_CODE_EXISTS("2005", "字典编码已存在"),
 
	FIRST_DELETE_NAME("4001","该单位已存在"),
	
	FIRST_DELETE_CODE("4002","编码重复"),
	
	FIRST_DELETE_PATH("4003","无图片");
	
	private String code;
	
	private String message;
	
	ServiceExceptionCodeEnum(String code, String message) {
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

