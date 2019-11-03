package com.attencecheckin.javabackend.dao;

import com.attencecheckin.javabackend.entity.Course;
import com.attencecheckin.javabackend.entity.CourseExample;

import java.util.Date;
import java.util.List;

import com.attencecheckin.javabackend.entity.LeaveApproval;
import org.apache.ibatis.annotations.Param;

public interface CourseMapper extends BaseDAO<Course, Integer>{
    int countByExample(CourseExample example);

    int deleteByExample(CourseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    List<Course> selectByExample(CourseExample example);

    Course selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Course record, @Param("example") CourseExample example);

    int updateByExample(@Param("record") Course record, @Param("example") CourseExample example);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    List<Course> selectCourseByTeacheridAndTime(@Param("teacherid") Integer teacherid,@Param("time")String time);
}