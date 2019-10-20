package com.attencecheckin.javabackend.dao;

import com.attencecheckin.javabackend.entity.ClassRoom;
import com.attencecheckin.javabackend.entity.ClassRoomExample;
import java.util.List;

import com.attencecheckin.javabackend.entity.Course;
import org.apache.ibatis.annotations.Param;

public interface ClassRoomMapper  extends BaseDAO<ClassRoom, Integer>{
    int countByExample(ClassRoomExample example);

    int deleteByExample(ClassRoomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ClassRoom record);

    int insertSelective(ClassRoom record);

    List<ClassRoom> selectByExample(ClassRoomExample example);

    ClassRoom selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ClassRoom record, @Param("example") ClassRoomExample example);

    int updateByExample(@Param("record") ClassRoom record, @Param("example") ClassRoomExample example);

    int updateByPrimaryKeySelective(ClassRoom record);

    int updateByPrimaryKey(ClassRoom record);
}