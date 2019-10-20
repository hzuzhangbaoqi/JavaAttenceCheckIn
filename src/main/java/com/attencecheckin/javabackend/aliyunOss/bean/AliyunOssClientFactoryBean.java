package com.attencecheckin.javabackend.aliyunOss.bean;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import lombok.Setter;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @program: javabackend
 * @description:
 * @author: zxf
 * @create: 2019-05-21 17:11
 **/
//Lombok 注解，
@Setter
public class AliyunOssClientFactoryBean implements FactoryBean<OSSClient>, InitializingBean, DisposableBean {
    private OSSClient ossClient;
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;

    @Override
    public void destroy() throws Exception {
        if (this.ossClient != null) {
            this.ossClient.shutdown();
        }
    }

    @Override
    public OSSClient getObject() throws Exception {return this.ossClient;}
    @Override
    public Class<?> getObjectType() {return OSSClient.class;}

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.endpoint, "'endpoint' must be not null");
        Assert.notNull(this.accessKeyId, "'accessKeyId' must be not null");
        Assert.notNull(this.accessKeySecret, "'accessKeySecret' must be not null");
        this.ossClient = new OSSClient(this.endpoint, this.accessKeyId, this.accessKeySecret);
        //注意说明 使用自定义域名时无法使用ossClient.listBuckets方法。
        List<Bucket> buckets = ossClient.listBuckets();
    }
}
