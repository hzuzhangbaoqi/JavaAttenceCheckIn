package com.attencecheckin.javabackend.entity;

import com.attencecheckin.javabackend.common.arg.BaseArg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="教室对象模型")
public class ClassRoom implements BaseArg {
    @ApiModelProperty(value = "教室id", name="id", dataType="Integer", required=false, hidden=false)
    private Integer id;
    @ApiModelProperty(value = "教室名称", name="classroomname", dataType="String", required=false, hidden=false)
    private String classroomname;
    @ApiModelProperty(value = "教室地址", name="address", dataType="String", required=false, hidden=false)
    private String address;

    @Override
    public void checkArgs() {

    }
}