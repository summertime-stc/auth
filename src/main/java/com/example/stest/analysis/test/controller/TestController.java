package com.example.stest.analysis.test.controller;


import com.example.stest.analysis.config.CustomException;
import com.example.stest.analysis.test.dao.TestDao;
import com.example.stest.analysis.test.domain.User;
import com.example.stest.analysis.util.IpandAddr.IpAndAddrUtil;
import com.example.stest.analysis.util.encryption.Encryption;
import com.example.stest.analysis.util.redis.RedisUtils;
import com.example.stest.common.controller.BaseController;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Controller
@Api(tags = "demo")
@Slf4j
public class TestController extends BaseController {

    @Value("${test.test.name}")
    private String name;

    @Autowired
    private TestDao testDao;


    @Resource
    private RedisUtils redisUtils;

    @PostMapping("/0000000login")
    @ApiOperation(value = "登录")
    @ResponseBody
    public String mst17(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "account") String account, @RequestParam(value = "password") String password) throws CustomException, IOException {
        if(StringUtils.isBlank(account)||StringUtils.isEmpty(account)){
            throw new CustomException("account不为空");
        }
        if(StringUtils.isBlank(password)||StringUtils.isEmpty(password)){
            throw new CustomException("password不为空");
        }

        User user=testDao.login(account,password);

        if(user!=null){
            //生成token
            Map<String, Object> map = new HashMap<>(1);
            map.put(user.getName(), user.getPassword()); //后面可以换成用户实体
            map.put("ip",IpAndAddrUtil.getIp(request));

            String jwt= Encryption.createtoken(map,user.getId(),3600*1000,"123456");

            //存入redis
            Map<String,String> map1=new HashMap<>();
            map1.put("name",user.getName());
            map1.put("token",jwt);
            redisUtils.selectDB(0);
            redisUtils.add(user.getId(),map1,30*60);

            //设置cookie
            Cookie cookie_token = new Cookie("token1",jwt);
            //设置失效时间
            cookie_token.setMaxAge(60*60*60);
            cookie_token.setPath("/");

            cookie_token.setHttpOnly(true);
            response.addCookie(cookie_token);
//            testDao.savetoken(user.getId(),jwt);
            request.getSession().setAttribute("user_name",user.getName());
            request.getSession().setAttribute("user_id",user.getId());
        }
        else{
            return "登录失败";
        }
//        response.sendRedirect("http://www.baidu.com");

        return "登录成功";
    }

    @PostMapping("/0000001out")
    @ApiOperation(value = "退出")
    @ResponseBody
    public String loginout(HttpServletRequest request,HttpServletResponse response) throws CustomException {
        String token=null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("token1")){
                    token=cookie.getValue();
                    break;
                }
            }
        }
        String userid;
        try {
            Claims claims =  Jwts.parser()
                    .setSigningKey("123456")
                    .parseClaimsJws(token)
                    .getBody();
            userid=claims.getSubject();
        }catch (ExpiredJwtException e){
            log.info("token解析出错");
            throw new CustomException("token已经失效");
        }
        if(userid!=null){
            redisUtils.selectDB(0);
            redisUtils.delete(userid);
            log.info("用户"+userid+"注销成功");
        }

        request.getSession().invalidate();
        return "注销成功";
    }

    @ApiOperation(value = "d1")
    @PostMapping("/test1")
    //事务
    @Transactional
    @ResponseBody
    public String test1(@RequestParam(value = "appid") String appid,@RequestParam(value = "timestamp") String timestamp,@RequestParam(value = "sign") String sign){
        System.out.println("hello1");
        return "成功";
    }

    @ApiOperation(value = "d2")
    @PostMapping("/test2")
    @ResponseBody
    public String test2(@RequestParam(value = "appid") String appid,@RequestParam(value = "timestamp") String timestamp,@RequestParam(value = "sign") String sign){
        System.out.println("hello2");
        return "成功";
    }

    @ApiOperation(value = "d3")
    @PostMapping("/test3")
    @ResponseBody
    public String test3(@RequestParam(value = "appid") String appid,@RequestParam(value = "timestamp") String timestamp,@RequestParam(value = "sign") String sign){
        System.out.println("hello3");
        return "成功";
    }

    @ApiOperation(value = "加密")
    @PostMapping("/encrypt")
    @ResponseBody
    public String encrypt(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("appid",Encryption.enAesCode("STCASD","AAAAAAAAAAAAAAAA"));
        Long time=System.currentTimeMillis();
        jsonObject.put("timestamp",Encryption.enAesCode(String.valueOf(time),"AAAAAAAAAAAAAAAA"));
        String sign= IpAndAddrUtil.getIp(request)+"STCASD"+time;
        jsonObject.put("sign",Encryption.encrytMD5(sign,"123456"));
        return jsonObject.toString();
    }

}
