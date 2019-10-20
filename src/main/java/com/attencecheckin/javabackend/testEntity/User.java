package com.attencecheckin.javabackend.testEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//使用该注解，会生成默认的无参构造器、所有属性的 getter、所有非 final 的属性的 setter 方法，重写 toString 方法，重写 equals 方法，重写 hashcode 方法
@Data
//Lombok 注解，用来为 POJO 类生成一个全参构造器
@AllArgsConstructor
//Lombok 无参构造注解
@NoArgsConstructor

@ApiModel(value = "用户实体类")
public class User {

    @ApiModelProperty(value = "用户唯一标识")
    private Long id;

    @ApiModelProperty(value = "用户姓名")
    private String username;

    @ApiModelProperty(value = "用户密码")
    private String password;

}
