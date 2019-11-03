package com.attencecheckin.javabackend.entity;

import com.attencecheckin.javabackend.common.arg.BaseArg;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value="学生课程对应模型")
public class Studentcourse implements BaseArg {
    private Integer id;

    private Integer courseid;

    private Integer studentid;

    @Override
    public void checkArgs() {

    }
}