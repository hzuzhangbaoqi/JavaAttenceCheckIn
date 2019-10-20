package com.attencecheckin.javabackend.entity;

import com.attencecheckin.javabackend.common.arg.BaseArg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@ApiModel(value="教师对象模型")
@Data
public class Teacher implements BaseArg {
    @ApiModelProperty(value = "职工号", name="id", dataType="Integer", required=false, hidden=false)
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
    @ApiModelProperty(value = "注册时间", name="registertime", dataType="Date", required=false, hidden=false)
    private Date registertime;
    @ApiModelProperty(value = "修改时间", name="updatetime", dataType="Date", required=false, hidden=false)
    private Date updatetime;

    @Override
    public void checkArgs() {

    }
}