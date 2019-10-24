package com.attencecheckin.javabackend.entity;

import com.attencecheckin.javabackend.common.arg.BaseArg;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@Data
@ApiModel(value="学生对象模型")
public class Student implements BaseArg {

    @ApiModelProperty(value = "学号", name="id", dataType="Integer", required=false, hidden=false)
    private Integer id;
    @ApiModelProperty(value = "微信id", name="openid", dataType="String", required=false, hidden=false)
    private String openid;
    @ApiModelProperty(value = "姓名", name="username", dataType="String", required=false, hidden=false)
    private String username;
    @ApiModelProperty(value = "性别", name="1：男，0：女", dataType="Integer", required=false, hidden=false)
    private Integer sex;
    @ApiModelProperty(value = "密码", name="password", dataType="String", required=false, hidden=false)
    private String password;
    @ApiModelProperty(value = "头像url", name="avaterurl", dataType="String", required=false, hidden=false)
    private String avaterurl;
    @ApiModelProperty(value = "手机号", name="avaterurl", dataType="String", required=false, hidden=false)
    private String phonenumber;
    @ApiModelProperty(value = "注册时间", name="registertime", dataType="Date", required=false, hidden=false,example = "2018-01-01 00:00:01")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date registertime;
    @ApiModelProperty(value = "修改时间", name="updatetime", dataType="Date", required=false, hidden=false,example = "2018-01-01 00:00:01")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updatetime;
    @ApiModelProperty(value = "班级id", name="classid", dataType="Integer", required=false, hidden=false)
    private Integer classid;
    @ApiModelProperty(value = "班级名称", name="classid", dataType="String", required=false, hidden=false)
    private String classname;

    @Override
    public void checkArgs() {

    }
}