package com.example.stest.analysis.config;

import com.example.stest.analysis.interceptor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Bean
    public Verifyinterceptor verifyinterceptor() {
        return new Verifyinterceptor();
    }

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Bean
    public Requesttimes requesttimes() {
        return new Requesttimes();
    }

    @Override
    //拦截器
    public void addInterceptors(InterceptorRegistry registry) {

        //单点拦截
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/**")
                //跳过RequestMapping映射名 /0000000login为接口
                .excludePathPatterns("/0000000login","/mst22","/mst23","/mst24","/static/**","/**/swagger-resources/**","/**/swagger-ui.html/**","/**/webjars/**","/**/index.html","/**/static/**","/**/error","/**/xx/**");

        //请求次数
        registry.addInterceptor(requesttimes()).addPathPatterns("/**")
                .excludePathPatterns("/0000000login","/0000001out","/static/**","/**/swagger-resources/**","/**/swagger-ui.html/**","/**/webjars/**","/**/index.html","/**/static/**","/**/error","/**/xx/**");


        registry.addInterceptor(verifyinterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/auth","/encrypt","/0000001out","/0000000login","/static/**","/**/swagger-resources/**","/**/swagger-ui.html/**","/**/webjars/**","/**/index.html","/**/static/**","/**/error","/**/xx/**");

//        registry.addInterceptor(new OneInterceptor()).addPathPatterns("/**");
//        registry.addInterceptor(new TwoInterceptor()).addPathPatterns("/**");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

        WebMvcConfigurer.super.addResourceHandlers(registry);

    }


    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 1.创建 redisTemplate 模版
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        // 2.关联 redisConnectionFactory
        template.setConnectionFactory(redisConnectionFactory);
        // 3.创建 序列化类
        GenericToStringSerializer genericToStringSerializer = new GenericToStringSerializer(Object.class);
        // 6.序列化类，对象映射设置
        // 7.设置 value 的转化格式和 key 的转化格式
        template.setValueSerializer(genericToStringSerializer);
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

}

