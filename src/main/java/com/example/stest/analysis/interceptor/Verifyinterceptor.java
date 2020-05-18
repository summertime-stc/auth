package com.example.stest.analysis.interceptor;

import com.example.stest.analysis.test.dao.TestDao;
import com.example.stest.analysis.util.encryption.Encryption;
import com.example.stest.analysis.util.redis.RedisUtils;
import com.example.stest.common.controller.ResultBase;
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

import static com.example.stest.common.codeEnum.ServiceExceptionCodeEnum.*;

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
            returnJson(response,ResultBase.error(TIMESTAMP.getCode(),TIMESTAMP.getMessage()));
            return false;
        }

        timestamp= Encryption.deAesCode(timestamp,"AAAAAAAAAAAAAAAA");
        Long rqtime;
        try{
            rqtime=Long.valueOf(timestamp);
        }catch (Exception e){
            log.info("timestamp入参不合法");
            returnJson(response,ResultBase.error(ILLEGALTIMESTAMP.getCode(),ILLEGALTIMESTAMP.getMessage()));
            return false;
        }
        Long crtime=System.currentTimeMillis();
        log.info("sss"+(crtime-rqtime));
        if (crtime-rqtime>10*1000*60) {
            log.info("请求已过期");
            returnJson(response,ResultBase.error(REQUESTTIMEOUT.getCode(),REQUESTTIMEOUT.getMessage()));
            return false;
        }

        if (StringUtils.isEmpty(appid)||StringUtils.isBlank(appid)){
            log.info("appid授权号不能为空");
            returnJson(response,ResultBase.error(APPIDNOTNULL.getCode(),APPIDNOTNULL.getMessage()));
            return false;
        }

        appid= Encryption.deAesCode(appid,"AAAAAAAAAAAAAAAA");
        if (appid==null){
            log.info("appid入参不合法");
            returnJson(response,ResultBase.error(ILLEGALAPPID.getCode(),ILLEGALAPPID.getMessage()));
            return false;
        }

        //验证sign
        String signauth= Encryption.encrytMD5(appid+rqtime,"123456");
        if (!signauth.equals(sign)){
            log.info("sign认证错误");
            returnJson(response,ResultBase.error(SIGNVALIDATIONERROR.getCode(),SIGNVALIDATIONERROR.getMessage()));
            return false;
        }

        //判断是否重复请求
        redisUtils.selectDB(3);
        if(redisUtils.hasKey(sign)){
            log.info("重复请求");
            returnJson(response,ResultBase.error(RESUBMITREQUEST.getCode(),RESUBMITREQUEST.getMessage()));
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
                returnJson(response,ResultBase.error(PERMISSIONDENIED.getCode(),PERMISSIONDENIED.getMessage()));
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

    //返回前端
    private void returnJson(HttpServletResponse response, Object obj) throws Exception{
        PrintWriter writer = null;
        JSONObject json=JSONObject.fromObject(obj);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json.toString());
        } catch (IOException e) {
            log.error("response error",e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}