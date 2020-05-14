package com.example.stest.common.controller;

import com.example.stest.common.codeEnum.ResponseCodeEnum;
import com.example.stest.common.codeEnum.ServiceExceptionCodeEnum;
import com.example.stest.common.util.Convert;
import com.example.stest.common.util.JSON;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Contoller基类
 * @date 2018.11.8
 * @author ccx
 */
public class BaseController {

	/**
     * validate params
     *
     * @param bindingResult
     * @return
     */
    protected BaseResponse validParams(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
			return processBindingError(fieldError);
        }
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(ServiceExceptionCodeEnum.SUCCESS.getCode());
        baseResponse.setMessage(ServiceExceptionCodeEnum.SUCCESS.getMessage());
        return baseResponse;
    }
 
    /**
     * 根据spring binding 错误信息自定义返回错误码和错误信息
     *
     * @param fieldError
     * @return
     */
    private BaseResponse processBindingError(FieldError fieldError) {
        String code = fieldError.getCode();
        switch (code) {
            case "NotEmpty":
                return returnError(ServiceExceptionCodeEnum.PARAM_EMPTY.getCode(), fieldError);
            case "NotBlank":
                return returnError(ServiceExceptionCodeEnum.PARAM_EMPTY.getCode(), fieldError);
            case "NotNull":
                return returnError(ServiceExceptionCodeEnum.PARAM_EMPTY.getCode(), fieldError);
            case "Pattern":
                return returnError(ServiceExceptionCodeEnum.PARAM_ERROR.getCode(), fieldError);
            case "Min":
                return returnError(ServiceExceptionCodeEnum.PARAM_ERROR.getCode(), fieldError);
            case "Max":
                return returnError(ServiceExceptionCodeEnum.PARAM_ERROR.getCode(), fieldError);
            case "Length":
                return returnError(ServiceExceptionCodeEnum.PARAM_ERROR.getCode(), fieldError);
            case "Range":
                return returnError(ServiceExceptionCodeEnum.PARAM_ERROR.getCode(), fieldError);
            case "Email":
                return returnError(ServiceExceptionCodeEnum.PARAM_ERROR.getCode(), fieldError);
            case "DecimalMin":
                return returnError(ServiceExceptionCodeEnum.PARAM_ERROR.getCode(), fieldError);
            case "DecimalMax":
                return returnError(ServiceExceptionCodeEnum.PARAM_ERROR.getCode(), fieldError);
            case "Size":
                return returnError(ServiceExceptionCodeEnum.PARAM_ERROR.getCode(), fieldError);
            case "Digits":
                return returnError(ServiceExceptionCodeEnum.PARAM_ERROR.getCode(), fieldError);
            case "Past":
                return returnError(ServiceExceptionCodeEnum.PARAM_ERROR.getCode(), fieldError);
            case "Future":
                return returnError(ServiceExceptionCodeEnum.PARAM_ERROR.getCode(), fieldError);
            default:
                return returnError(ServiceExceptionCodeEnum.UNKNOWN_ERROR.getCode(), fieldError);
        }
    }
    
    private BaseResponse returnError(String code, FieldError fieldError) {
    	BaseResponse baseResponse = new BaseResponse();
    	baseResponse.setCode(code);
//    	baseResponse.setData(fieldError);
//    	baseResponse.setMessage(fieldError.getField() + " " + fieldError.getDefaultMessage());
    	baseResponse.setMessage(fieldError.getDefaultMessage());
    	return baseResponse;
	}
    
    private BaseResponse returnError(ResponseCodeEnum validateCodeEnum, FieldError fieldError) {
    	return returnError(validateCodeEnum.getCode(), fieldError);
	}
	
	public String getString(HttpServletRequest request, String name) {
		return request.getParameter(name);
	}
	
	public int getInt(HttpServletRequest request, String name) {
		return Convert.toInt(getString(request, name));
	}
	
	public boolean getBoolean(HttpServletRequest request, String name) {
		return Convert.toBoolean(getString(request, name));
	}
	
	public Object getObject(HttpServletRequest request, String name) {
		return JSON.decode(getString(request, name));
	}
	
	public HashMap getHashMap(HttpServletRequest request, String name) {
		return (HashMap)JSON.decode(getString(request, name));
	}
	
	public ArrayList getArrayList(HttpServletRequest request, String name) {
		return (ArrayList)JSON.decode(getString(request, name));
	}
	
	public void renderJson(HttpServletResponse response, Object obj) throws IOException {
		String json = JSON.encode(obj);
	    response.getWriter().write(json);
	}
	
	public void renderText(HttpServletResponse response, String text) throws IOException {
	    response.getWriter().write(text);
	}
}
