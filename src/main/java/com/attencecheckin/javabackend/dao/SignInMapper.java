package com.attencecheckin.javabackend.dao;

import com.attencecheckin.javabackend.entity.SignIn;
import com.attencecheckin.javabackend.entity.SignInExample;
import java.util.List;
import java.util.Map;

import com.attencecheckin.javabackend.entity.Student;
import org.apache.ibatis.annotations.Param;

public interface SignInMapper extends BaseDAO<SignIn,Integer> {
    int countByExample(SignInExample example);

    int deleteByExample(SignInExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SignIn record);

    int insertSelective(SignIn record);

    List<SignIn> selectByExample(SignInExample example);

    SignIn selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SignIn record, @Param("example") SignInExample example);

    int updateByExample(@Param("record") SignIn record, @Param("example") SignInExample example);

    int updateByPrimaryKeySelective(SignIn record);

    int updateByPrimaryKey(SignIn record);
    List<SignIn> showSignin(@Param("courseid") Integer courseid,@Param("time")String time,@Param("signtype") Integer signtype);
    List<Map<String,Object>> getAttendanceStatistics(@Param("signtimeStart")String signtimeStart ,@Param("signtimeEnd")String signtimeEnd,@Param("studentid")Integer studentid);
    List<Map<String,Object>> getAbnormal(@Param("studentids")List<Integer> studentids ,@Param("status")List<Integer> status);


}