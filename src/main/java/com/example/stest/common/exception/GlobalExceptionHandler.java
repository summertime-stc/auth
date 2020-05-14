package com.example.stest.common.exception;

import com.example.stest.common.util.JSON;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * GlobalExceptionHandler
 * @date 2018.11.8
 * @author ccx
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public String jsonErrorHandler(HttpServletRequest request, HttpServletResponse response, ServiceException serviceException) throws Exception {
		ExceptionInfo<ServiceException> exceptionInfo = new ExceptionInfo<>();
		exceptionInfo.setMessage(serviceException.getErrorMsg());
		exceptionInfo.setCode(serviceException.getErrorCode());
//		exceptionInfo.setData(serviceException);
//		exceptionInfo.setUrl(request.getRequestURL().toString());
//		serviceException.printStackTrace();
		String json = JSON.encode(exceptionInfo);
        return json;
    }
	
//	@ExceptionHandler(value = ServiceException.class)
//  @ResponseBody
//  public String jsonErrorHandler(HttpServletRequest request, HttpServletResponse response, ServiceException serviceException) throws Exception {
//		serviceException.printStackTrace();
//		String json = JSON.encode(serviceException);
//      return json;
//  }
	
}
