package com.example.stest.analysis.util.error;

/**
 * User: kingt
 * Date: 2018/8/8_10:07
 */
public class ErrorUtil extends RuntimeException {
    private Integer errorType;
    private String errorText;

    public Integer getErrorType() {
        return this.errorType;
    }

    public void setErrorType(Integer errorType) {
        this.errorType = errorType;
    }

    public String getErrorText() {
        return this.errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public ErrorUtil(String errorText) {

        this.errorType = 1;
        this.errorText = errorText;
    }

    public ErrorUtil(Integer errorType, String errorText) {
        this.errorType = errorType;
        this.errorText = errorText;
    }

    public String getMessage() {
        return this.errorText;
    }
}