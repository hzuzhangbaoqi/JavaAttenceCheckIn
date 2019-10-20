package com.attencecheckin.javabackend.listener.event;

import org.springframework.context.ApplicationEvent;

/**
 * @program: javabackend
 * @description: 自定义事件
 * @author: zxf
 * @create: 2019-10-14 13:08
 **/
public class MyEvent  extends ApplicationEvent {
    private Object post;

    public MyEvent(Object source, Object post) {
        super(source);
        this.post = post;
    }

    public Object getPost() {
        return post;
    }

    public void setPost(Object post) {
        this.post = post;
    }
}
