package com.attencecheckin.javabackend.entity;

import com.attencecheckin.javabackend.common.arg.BaseArg;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@Data
@ApiModel(value="登录日志对象模型")
public class LoginInfoLog implements BaseArg {
    @ApiModelProperty(value = "登录日志id", name="id", dataType="Integer", required=false, hidden=false)
    private Integer id;
    @ApiModelProperty(value = "登录Id", name="loginid", dataType="Integer", required=false, hidden=false)
    private Integer loginid;
    @ApiModelProperty(value = "用户类型 1老师 2学生", name="loginusertype", dataType="Integer", required=false, hidden=false)
    private Integer loginusertype;
    @ApiModelProperty(value = "登录时间", name="logintime", dataType="Date", required=false, hidden=false,example = "2018-01-01 00:00:01")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date logintime;
    @ApiModelProperty(value = "登录Ip", name="ip", dataType="String", required=false, hidden=false)
    private String ip;
    @ApiModelProperty(value = "登录GPS位置", name="local", dataType="String", required=false, hidden=false)
    private String local;
    @ApiModelProperty(value = "mac设备地址", name="local", dataType="String", required=false, hidden=false)
    private String macaddress;
    @ApiModelProperty(value = "设备名称", name="local", dataType="String", required=false, hidden=false)
    private String macname;

    @Override
    public void checkArgs() {

    }
}