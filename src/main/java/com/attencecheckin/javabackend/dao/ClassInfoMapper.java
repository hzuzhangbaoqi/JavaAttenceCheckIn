package com.attencecheckin.javabackend.dao;

import com.attencecheckin.javabackend.entity.ClassInfo;
import com.attencecheckin.javabackend.entity.ClassInfoExample;
import java.util.List;
import java.util.Map;

import com.attencecheckin.javabackend.entity.ClassRoom;
import org.apache.ibatis.annotations.Param;

public interface ClassInfoMapper extends BaseDAO<ClassInfo, Integer>{
    int countByExample(ClassInfoExample example);

    int deleteByExample(ClassInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ClassInfo record);

    int insertSelective(ClassInfo record);

    List<ClassInfo> selectByExample(ClassInfoExample example);

    ClassInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ClassInfo record, @Param("example") ClassInfoExample example);

    int updateByExample(@Param("record") ClassInfo record, @Param("example") ClassInfoExample example);

    int updateByPrimaryKeySelective(ClassInfo record);

    int updateByPrimaryKey(ClassInfo record);
    List<Map<String,Object>> selectKV();
}