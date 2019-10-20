package com.attencecheckin.javabackend.dao;

import com.attencecheckin.javabackend.entity.LoginInfoLog;
import com.attencecheckin.javabackend.entity.LoginInfoLogExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface LoginInfoLogMapper extends BaseDAO<LoginInfoLog, Integer>{
    int countByExample(LoginInfoLogExample example);

    int deleteByExample(LoginInfoLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LoginInfoLog record);

    int insertSelective(LoginInfoLog record);

    List<LoginInfoLog> selectByExample(LoginInfoLogExample example);

    LoginInfoLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LoginInfoLog record, @Param("example") LoginInfoLogExample example);

    int updateByExample(@Param("record") LoginInfoLog record, @Param("example") LoginInfoLogExample example);

    int updateByPrimaryKeySelective(LoginInfoLog record);

    int updateByPrimaryKey(LoginInfoLog record);
}