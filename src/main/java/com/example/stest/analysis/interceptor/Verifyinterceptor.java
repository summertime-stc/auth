package com.example.stest.analysis.interceptor;

import com.example.stest.analysis.test.dao.TestDao;
import com.example.stest.analysis.util.IpandAddr.IpAndAddrUtil;
import com.example.stest.analysis.util.encryption.Encryption;
import com.example.stest.analysis.util.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Slf4j
public class Verifyinterceptor implements HandlerInterceptor {

    @Autowired
    private TestDao testDao;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("----------------拦截验证参数及权限----------");
        String appid=request.getParameter("appid");
        String timestamp=request.getParameter("timestamp");
        String sign=request.getParameter("sign");
        if (StringUtils.isEmpty(timestamp)||StringUtils.isBlank(timestamp)){
            log.info("timestamp时间戳不能为空");
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("code","500");
            jsonObject.put("message","timestamp时间戳不能为空");
            returnJson(response,jsonObject.toString());
            return false;
        }

        timestamp= Encryption.deAesCode(timestamp,"AAAAAAAAAAAAAAAA");
        Long rqtime;
        try{
            rqtime=Long.valueOf(timestamp);
        }catch (Exception e){
            log.info("timestamp入参不合法");
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("code","500");
            jsonObject.put("message","timestamp入参不合法");
            returnJson(response,jsonObject.toString());
            return false;
        }
        Long crtime=System.currentTimeMillis();
        log.info("sss"+(crtime-rqtime));
        if (crtime-rqtime>10*1000*60) {
            log.info("请求已过期");
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("code","500");
            jsonObject.put("message","请求已过期");
            returnJson(response,jsonObject.toString());
            return false;
        }

        if (StringUtils.isEmpty(appid)||StringUtils.isBlank(appid)){
            log.info("appid授权号不能为空");
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("code","500");
            jsonObject.put("message","appid授权号不能为空");
            returnJson(response,jsonObject.toString());
            return false;
        }

        appid= Encryption.deAesCode(appid,"AAAAAAAAAAAAAAAA");
        if (appid==null){
            log.info("appid入参不合法");
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("code","500");
            jsonObject.put("message","appid入参不合法");
            returnJson(response,jsonObject.toString());
            return false;
        }

        //验证sign
        String signauth= Encryption.encrytMD5(IpAndAddrUtil.getIp(request)+appid+rqtime,"123456");
        if (!signauth.equals(sign)){
            log.info("sign认证错误");
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("code","500");
            jsonObject.put("message","sign认证错误！");
            returnJson(response,jsonObject.toString());
            return false;
        }

        //判断是否重复请求
        redisUtils.selectDB(3);
        if(redisUtils.hasKey(sign)){
            log.info("重复请求");
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("code","500");
            jsonObject.put("message","重复请求！");
            returnJson(response,jsonObject.toString());
            return false;
        }
        else{
            redisUtils.set(sign,sign,10*60);
        }

        //接口权限拦截
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //获取请求方法名
            String m=handlerMethod.getMethod().getName();
            List<String> list=testDao.getauthmethod(appid);
            if (list.contains(m)){
                log.info("拥有该接口访问权限");
                return true;
            }
            else{
                log.info("无该接口访问权限");
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("code","500");
                jsonObject.put("message","appid无该接口访问权限");
                returnJson(response,jsonObject.toString());
                return false;
            }
        }
        return true;
    }


    //返回前端
    private void returnJson(HttpServletResponse response, String json) throws Exception{
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);
        } catch (IOException e) {
            log.error("response error",e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}