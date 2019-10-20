package com.attencecheckin.javabackend.config;

import com.attencecheckin.javabackend.interceptor.Interceptor;
import com.attencecheckin.javabackend.interceptor.Interceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: javabackend
 * @description:
 * @author: zxf
 * @create: 2019-10-14 13:20
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    /*
    extends WebMvcConfigurationSupport

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Interceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    *//**
     * 用来指定静态资源不被拦截，否则继承 WebMvcConfigurationSupport 这种方式会导致静态资源无法直接访问
     * @param registry
     *//*
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }*/


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 实现 WebMvcConfigurer 不会导致静态资源被拦截
        registry.addInterceptor(new Interceptor())
                .addPathPatterns("/interceptor")
                // 放行 Swagger
                .excludePathPatterns("/swagger-resources/**");;
    }
}
