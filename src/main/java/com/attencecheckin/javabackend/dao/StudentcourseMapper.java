package com.attencecheckin.javabackend.dao;

import com.attencecheckin.javabackend.entity.SignIn;
import com.attencecheckin.javabackend.entity.Studentcourse;
import com.attencecheckin.javabackend.entity.StudentcourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentcourseMapper extends BaseDAO<Studentcourse,Integer> {
    int countByExample(StudentcourseExample example);

    int deleteByExample(StudentcourseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Studentcourse record);

    int insertSelective(Studentcourse record);

    List<Studentcourse> selectByExample(StudentcourseExample example);

    Studentcourse selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Studentcourse record, @Param("example") StudentcourseExample example);

    int updateByExample(@Param("record") Studentcourse record, @Param("example") StudentcourseExample example);

    int updateByPrimaryKeySelective(Studentcourse record);

    int updateByPrimaryKey(Studentcourse record);
}