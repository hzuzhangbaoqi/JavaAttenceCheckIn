package com.attencecheckin.javabackend.aliyunOss.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @program: javabackend
 * @description:
 * @author: zxf
 * @create: 2019-05-21 17:07
 **/

@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
//使用该注解，会生成默认的无参构造器、所有属性的 getter、所有非 final 的属性的 setter 方法，重写 toString 方法，重写 equals 方法，重写 hashcode 方法
@Data
//Lombok 注解，用来为 POJO 类生成一个全参构造器
@AllArgsConstructor
//Lombok 注解，用来为 POJO 类生成一个无参构造器
@NoArgsConstructor
public class AliyunOssProperties {
    /**endpoint*/
    private String endpoint;
    /**accessKeyId*/
    private String accessKeyId;
    /**accessKeySecret*/
    private String accessKeySecret;
}
