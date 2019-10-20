package com.attencecheckin.javabackend.handler;

import com.attencecheckin.javabackend.handler.annotate.Duration;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @program: javabackend
 * @description: 自定义的耗时Aop
 * @author: zxf
 * @create: 2019-10-11 17:53
 **/
@Aspect
@Component
public class DurationAspect {
    private final static Logger log = LoggerFactory.getLogger(DurationAspect.class);
    ThreadLocal<Long> beginTime = new ThreadLocal<>();

    @Pointcut("@annotation(duration)")
    public void serviceStatistics(Duration duration) {
    }

    @Before("serviceStatistics(duration)")
    public void doBefore(JoinPoint joinPoint, Duration duration) {
        log.info("====开始计算耗时====");
        // 获取签名
        Signature signature = joinPoint.getSignature();
        // 获取切入的包名
        String declaringTypeName = signature.getDeclaringTypeName();
        // 获取即将执行的方法名
        String funcName = signature.getName();
        log.info("即将执行方法为: {}，属于{}包", funcName, declaringTypeName);
        // 记录请求到达时间
        beginTime.set(System.currentTimeMillis());
        log.info("耗时计算的备注说明 note:{}", duration.note());
    }

    @After("serviceStatistics(duration)")
    public void doAfter(JoinPoint joinPoint,Duration duration) {
        log.info("====结束计算耗时====");
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();
        log.info("方法{}已经执行完，耗时:{}ms, note:{}", method,System.currentTimeMillis() - beginTime.get(), duration.note());
    }

}
