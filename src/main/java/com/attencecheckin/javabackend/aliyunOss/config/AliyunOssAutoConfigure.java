package com.attencecheckin.javabackend.aliyunOss.config;

import com.aliyun.oss.OSSClient;
import com.attencecheckin.javabackend.aliyunOss.bean.AliyunOssClientFactoryBean;
import com.attencecheckin.javabackend.aliyunOss.bean.AliyunOssProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: javabackend
 * @description:
 * @author: zxf
 * @create: 2019-05-21 17:08
 **/

@Configuration
@ConditionalOnClass({OSSClient.class})
/*junits 测试的时候才需要用到*/
/*@EnableConfigurationProperties(AliyunOssProperties.class)*/
public class AliyunOssAutoConfigure {
    @Autowired
    private AliyunOssProperties properties;

    public AliyunOssAutoConfigure(final AliyunOssProperties aliyunOssProperties) {
        this.properties = aliyunOssProperties;
    }

    @Bean
    public AliyunOssClientFactoryBean ossClientFactoryBean() {
        final AliyunOssClientFactoryBean factoryBean = new AliyunOssClientFactoryBean();
        factoryBean.setEndpoint(this.properties.getEndpoint());
        factoryBean.setAccessKeyId(this.properties.getAccessKeyId());
        factoryBean.setAccessKeySecret(this.properties.getAccessKeySecret());
        return factoryBean;
    }
}
