package com.attencecheckin.javabackend.entity;

import com.attencecheckin.javabackend.common.arg.BaseArg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@Data
@ApiModel(value="签到对象模型")
public class SignIn implements BaseArg {
    @ApiModelProperty(value = "签到id", name="id", dataType="Integer", required=false, hidden=false)
    private Integer id;
    @ApiModelProperty(value = "签到学生ID", name="studentid", dataType="Integer", required=false, hidden=false)
    private Integer studentid;
    @ApiModelProperty(value = "签到时间", name="signtime", dataType="Date", required=false, hidden=false)
    private Date signtime;
    @ApiModelProperty(value = "签到状态 0未签到 1已签到 2请假 3旷课", name="status", dataType="Integer", required=false, hidden=false)
    private Integer status;
    @ApiModelProperty(value = "课程Id", name="courseid", dataType="Integer", required=false, hidden=false)
    private Integer courseid;
    @ApiModelProperty(value = "位置是否可以 0位置正常 1位置可疑", name="status", dataType="Integer", required=false, hidden=false)
    private Integer suslocationstatus;
    @ApiModelProperty(value = "可疑位置经纬度", name="status", dataType="Integer", required=false, hidden=false)
    private String suslocation;

    @Override
    public void checkArgs() {
        
    }
}