package com.attencecheckin.javabackend.dao;

import com.attencecheckin.javabackend.dao.impl.AbstractMybatisBaseDAO;
import com.attencecheckin.javabackend.entity.Student;
import com.attencecheckin.javabackend.entity.StudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentMapper  extends BaseDAO<Student,Integer> {
    int countByExample(StudentExample example);

    int deleteByExample(StudentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    List<Student> selectByExample(StudentExample example);

    Student selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByExample(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    List<Student> getStudentidsByCourse(@Param("courseids") List<String>courseids);
}