package com.example.stest.common.util;

import java.util.HashMap;
import java.util.Map;

public class SystemConfig {
	private static Map<String, String> config = new HashMap<String, String>(200);
	static {
		config.put("HSBP_DOMAIN", "http://localhost:8089"); //域名
		config.put("LOGIN_MAPPING", "/login"); //默认登陆mapping
		config.put("LOGOUT_MAPPING", "/logout"); //退出登陆mapping
		config.put("LOGIN_USERNAME", "userName"); //登陆用户字段名称
		config.put("LOGIN_PASSWORD", "password"); //登陆密码字段名称
		config.put("LOGIN_DEFAULT_TARGET_URL", "/security/html/index.html"); //默认登陆后的跳转页
		config.put("LOGIN_PARAMETER_TARGET_URL", "targetUrl"); //登陆请求参数，会根据这个参数的value跳转对应页面
		config.put("LOGIN_PARAMETER_CUSTOMER", "customer"); //登陆请求参数，会根据这个参数（0/1）决定是否根据特定逻辑跳转专属页面
		config.put("LOGIN_FAILURE_URL", "/loginError"); //登陆验证失败后的跳转页面
		config.put("LOGIN_RESPONSE_JSON", "true"); //登陆返回json
		config.put("LOGIN_ENCODE", "UTF-8"); //返回JSON编码类型
		config.put("AUTHORITY_FORBIDDEN", "/403forbidden"); //未授权
	}
	
	public static String get(String key) {
		return config.get(key);
	}
}
