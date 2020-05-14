package com.example.stest.common.exception;

/**
 * ServiceException
 * @date 2018.11.8
 * @author ccx
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	private String errorMsg;

	public ServiceException(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
