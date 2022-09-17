package com.proj.KnitMarket.interceptor;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import java.util.Collections;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor())
                .addPathPatterns("/cart/**")
                .addPathPatterns("/item/**")
                .addPathPatterns("/mypage/**")
                .addPathPatterns("/order/**")
                .excludePathPatterns("/item/detail/**");
        //https://kauth.kakao.com/**","/kakaoLogin/**","/css/**", "/img/**", "/plugin/**", "/uploadImg/**","/Source/**","/login","/","/js/**","/item/detail/**
    }
}
