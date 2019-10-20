package com.attencecheckin.javabackend.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;

/**
 * @program: javabackend
 * @description: 监听器 使用 ApplicationListener 来初始化一些数据到 application 域中的监听器
 * @author: zxf
 * @create: 2019-10-14 12:54
 **/
@Component
public class ServletContextListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // 先获取到 application 上下文
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
        // 获取对应的 service
      /*  PostsService postsService = applicationContext.getBean(PostsService.class);
        Posts post = postsService.getPost(1L);
        // 获取 application 域对象，将查到的信息放到 application 域中
        ServletContext application = applicationContext.getBean(ServletContext.class);
        application.setAttribute("post", post);*/
    }
}
