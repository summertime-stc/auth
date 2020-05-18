package com.example.stest.analysis.interceptor;


import com.example.stest.analysis.util.IpandAddr.IpAndAddrUtil;
import com.example.stest.analysis.util.redis.RedisUtils;
import com.example.stest.common.controller.ResultBase;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.example.stest.common.codeEnum.ServiceExceptionCodeEnum.FREQUENTREQUESTS;

@Slf4j
public class Requesttimes implements HandlerInterceptor {
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        log.info("------------拦截请求次数器层--------");
        String ip= IpAndAddrUtil.getIp(request);

        redisUtils.selectDB(1);
        if(!redisUtils.hasKey(ip)){
            redisUtils.set(ip,"0",60);
        }

        if(Integer.valueOf((String)redisUtils.get(ip))>20){
            redisUtils.expire(ip,60);
//            throw new CustomException("请求过于频繁！！请稍后再试");

            log.info("已拦截请求："+ip);
            returnJson(response, ResultBase.error(FREQUENTREQUESTS.getCode(),FREQUENTREQUESTS.getMessage()));
            return false;
        }
        else{
            redisUtils.increment(ip,1);
        }
        log.info("            拦截请求次数器层结束");
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
