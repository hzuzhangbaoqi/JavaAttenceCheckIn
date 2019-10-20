package com.attencecheckin.javabackend.listener;

import com.attencecheckin.javabackend.listener.event.MyEvent;
import org.springframework.context.ApplicationListener;

/**
 * @program: javabackend
 * @description: 自定义监听器，监听 MyEvent 事件
 * @author: zxf
 * @create: 2019-10-14 13:09
 **/
public class MyEventListener  implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        // 把事件中的信息获取到
      /*  Posts post = myEvent.getPost();
        // 处理事件，实际项目中可以通知别的微服务或者处理其他逻辑等
        System.out.println("id：" + post.getPostsid());
        System.out.println("url：" + post.getPostsurl());*/

    }
}
