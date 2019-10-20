package com.attencecheckin.javabackend.handler.exception;

import com.attencecheckin.javabackend.common.enumer.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: javabackend
 * @description:定义异常信息
 * @author: zxf
 * @create: 2019-05-22 09:25
 **/
//使用该注解，会生成默认的无参构造器、所有属性的 getter、所有非 final 的属性的 setter 方法，重写 toString 方法，重写 equals 方法，重写 hashcode 方法
@Data
//Lombok 注解，用来为 POJO 类生成一个全参构造器
@AllArgsConstructor
@NoArgsConstructor
public class BusinessErrorException extends RuntimeException{
    /**异常码*/
    private String code;
    /**异常提示信息*/
    private String message;


    public BusinessErrorException(ResultEnum businessMsgEnum) {
        this.code = businessMsgEnum.val();
        this.message = businessMsgEnum.msg();
    }
}
