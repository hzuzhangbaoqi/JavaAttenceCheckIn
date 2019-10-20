package com.attencecheckin.javabackend.entity;

import com.attencecheckin.javabackend.common.arg.BaseArg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="班级对象模型")
public class ClassInfo implements BaseArg {
    @ApiModelProperty(value = "班级id", name="id", dataType="Integer", required=false, hidden=false)
    private Integer id;
    @ApiModelProperty(value = "班级名称", name="classname", dataType="String", required=false, hidden=false)
    private String classname;

    @Override
    public void checkArgs() {

    }
}