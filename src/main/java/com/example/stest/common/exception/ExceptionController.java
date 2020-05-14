package com.example.stest.common.exception;

import com.example.stest.common.codeEnum.ResponseCodeEnum;
import com.example.stest.common.util.SystemConfig;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ApiIgnore
@Configuration
@RequestMapping("/error")
public class ExceptionController extends BasicErrorController {
	
	private static String HSBP_DOMAIN = SystemConfig.get("HSBP_DOMAIN");
	
	public ExceptionController() {
		super(new DefaultErrorAttributes(), new ErrorProperties());
	}
	
	@Override
	@RequestMapping
	@ResponseBody
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
		Map<String, Object> body = new HashMap<String, Object>();
		HttpStatus status = getStatus(request);
		if("404".equals(status.value() + "")) {
			body.put("code", ResponseCodeEnum.NOT_FOUND.getCode());
			body.put("message", ResponseCodeEnum.NOT_FOUND.getMessage());
		} else if("500".equals(status.value() + "")) {
			body.put("code", ResponseCodeEnum.SERVICE_ERROR.getCode());
			body.put("message", ResponseCodeEnum.SERVICE_ERROR.getMessage());
		} else {
			body.put("code", status);
			body.put("message", status);
		}
		body.put("requestUrl", HSBP_DOMAIN + request.getServletPath());
		return new ResponseEntity<>(body, status);
	}
	
	@Override
	@RequestMapping(produces = "text/html")
	public ModelAndView errorHtml(HttpServletRequest request,
                                  HttpServletResponse response) {
		HttpStatus status = getStatus(request);
		Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(
				request, isIncludeStackTrace(request, MediaType.TEXT_HTML)));
		response.setStatus(status.value());
		ModelAndView modelAndView = resolveErrorView(request, response, status, model);
		if("404".equals(status.value() + "")) {
			return new ModelAndView("404notFound", model);
		}else {
			return (modelAndView != null) ? modelAndView : new ModelAndView("error", model);
		}
		
	}
}
