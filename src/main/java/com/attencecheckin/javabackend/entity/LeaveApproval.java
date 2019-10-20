package com.attencecheckin.javabackend.entity;

import com.attencecheckin.javabackend.common.arg.BaseArg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@Data
@ApiModel(value="审批对象模型")
public class LeaveApproval implements BaseArg {
    @ApiModelProperty(value = "审批id", name="id", dataType="Integer", required=false, hidden=false)
    private Integer id;
    @ApiModelProperty(value = "学生id", name="id", dataType="Integer", required=false, hidden=false)
    private Integer studentid;
    @ApiModelProperty(value = "请假理由", name="coursename", dataType="String", required=false, hidden=false)
    private String leavereason;
    @ApiModelProperty(value = "请假审批状态 0未审批 1已审批", name="status", dataType="Integer", required=false, hidden=false)
    private Integer status;
    @ApiModelProperty(value = "课程Id", name="courseid", dataType="Integer", required=false, hidden=false)
    private Integer courseid;
    @ApiModelProperty(value = "请假提交时间", name="leavesubtime", dataType="Date", required=false, hidden=false)
    private Date leavesubtime;
    @ApiModelProperty(value = "请假审批时间", name="leavesubtime", dataType="Date", required=false, hidden=false)
    private Date approvaltime;

    @Override
    public void checkArgs() {

    }
}