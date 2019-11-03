package com.attencecheckin.javabackend.entity;

import com.attencecheckin.javabackend.common.arg.BaseArg;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value="课程对象模型")
public class Course implements BaseArg {
    @ApiModelProperty(value = "课程id", name="id", dataType="Integer", required=false, hidden=false)
    private Integer id;
    @ApiModelProperty(value = "课程名称", name="coursename", dataType="String", required=false, hidden=false)
    private String coursename;
    @ApiModelProperty(value = "上课时间", name="coursestarttime", dataType="Date", required=false, hidden=false,example = "2018-01-01 00:00:01")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date coursestarttime;
    @ApiModelProperty(value = "下课时间", name="coursestarttime", dataType="Date", required=false, hidden=false,example = "2018-01-01 00:00:01")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date courseendtime;
    @ApiModelProperty(value = "星期几", name="week", dataType="Integer", required=false, hidden=false)
    private Integer week;
    @ApiModelProperty(value = "当天第几节课", name="jieci", dataType="Integer", required=false, hidden=false)
    private Integer jieci;
    @ApiModelProperty(value = "班级名称", name="classid", dataType="Integer", required=false, hidden=false)
    private Integer classid;
    @ApiModelProperty(value = "教室Id", name="classroomid", dataType="Integer", required=false, hidden=false)
    private Integer classroomid;

    private Integer teacherid;

    @Override
    public void checkArgs() {

    }
}