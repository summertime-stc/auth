package com.example.stest.analysis.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;


public class TwoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
//        InputStream fis=this.getClass().getResourceAsStream("/time.properties");
        File file=new File("src/main/resources/time.properties");

        if(!file.exists()){
            file.createNewFile();
        }
        FileInputStream fis=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(fis);
        String time=prop.getProperty("time");
        int count=0;
        if(time!=null){
            count=Integer.valueOf(time);
            if(count>400){
                System.out.println("使用次数已用完");
                return false;
            }
        }
        count++;
        System.out.println("剩余次数:"+(401-count)+"/400");
        prop.setProperty("time", String.valueOf(count));
        FileOutputStream fos=new FileOutputStream(file);
        prop.store(fos,"");
        fis.close();
        fos.close();
        return true;
    }
}
