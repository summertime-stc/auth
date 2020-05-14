package com.example.stest.analysis.interceptor;


import com.example.stest.analysis.util.IpandAddr.IpAndAddrUtil;
import com.example.stest.analysis.util.redis.RedisUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    private final String USER="user";
    private final String TOKEN="token1";
    private final String TARGETPATH="targetpath";
    private final String LOGINPATH="http://www.baidu.com";
    private final String SSOPATH="/auth";
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        log.info("--------------单点登录拦截器层---------");

        String targetpath=request.getParameter(TARGETPATH);
        String path=request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/"),request.getRequestURI().length());
        System.out.println("--"+path);
        String token=null;
        token=getToken(token,request);

        if(StringUtils.isBlank(token)||StringUtils.isEmpty(token)){
            System.out.println("跳转至登录页");
            response.sendRedirect(LOGINPATH);
            return false;
        }

        String userid;
        String ip;
        try {
            Claims claims =  Jwts.parser()
                    .setSigningKey("123456")
                    .parseClaimsJws(token)
                    .getBody();
            userid=claims.getSubject();
            ip= (String) claims.get("ip");
            System.out.println("====="+ip);
        }catch (ExpiredJwtException e){
            userid=e.getClaims().getSubject();
            ip= (String) e.getClaims().get("ip");
        }

        if (!ip.equals(IpAndAddrUtil.getIp(request))){
            log.info("rookie被劫持");
            response.sendRedirect(LOGINPATH);
            return false;
        }

        redisUtils.selectDB(0);
        if(redisUtils.hasKey(userid)){
            if(redisUtils.getMapString(userid,"token").equals(token)&&token!=null){
                //单点验证
                if(StringUtils.equals(path,SSOPATH)){
                    if(!request.getMethod().equals("GET")){
                        log.info("Authentication method not supported: " + request.getMethod());
                        throw new ServletException("Authentication method not supported: " + request.getMethod());
                    }
                    log.info("targetpath:"+targetpath);
                    redisUtils.expire(userid,60*30);
                    response.sendRedirect(targetpath);
                    return false;
                }
                else{
                    if(request.getSession().getAttribute(USER)==null){
                        request.getSession().setAttribute(USER,userid);
                    request.getSession().setAttribute("name",redisUtils.getMapString(userid,"name"));
                }
                    redisUtils.expire(userid,60*30);
                    return true;
                }
            }
            else{
                //token不一致，异地登录
                response.sendRedirect(LOGINPATH);
                return false;
            }
        }
        else{
            //redis用户id不存在，已注销或失效
            log.info("token已失效");
            response.sendRedirect(LOGINPATH);
            return false;
        }

    }

    String getToken(String token,HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals(TOKEN)){
                    token=cookie.getValue();
                    break;
                }
            }
        }
        return token;
    }
}
