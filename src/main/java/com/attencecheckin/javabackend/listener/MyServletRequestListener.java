package com.attencecheckin.javabackend.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionListener;

/**
 * @program: javabackend
 * @description: 监听客户端请求 Servlet Request 对象
 *                  使用 ServletRequestListener 获取访问信息
 * @author: zxf
 * @create: 2019-10-14 13:07
 **/
@Component
public class MyServletRequestListener implements ServletRequestListener {
    private final static Logger logger = LoggerFactory.getLogger(MyServletRequestListener.class);
    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        logger.info("session id为：{}", request.getRequestedSessionId());
        logger.info("request url为：{}", request.getRequestURL());

        request.setAttribute("name", "11");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

        logger.info("request end");
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        logger.info("request域中保存的name值为：{}", request.getAttribute("name"));

    }
}
