package com.attencecheckin.javabackend.service.impl;

import com.attencecheckin.javabackend.dao.StudentMapper;
import com.attencecheckin.javabackend.entity.Student;
import com.attencecheckin.javabackend.entity.StudentExample;
import com.attencecheckin.javabackend.entity.Studentcourse;
import com.attencecheckin.javabackend.entity.StudentcourseExample;
import com.attencecheckin.javabackend.service.StudentService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
* @Description: StudentService接口实现类
* @author 
* @date 2019/10/17 02:38
*/
@Service
public class StudentServiceImpl extends AbstractBaseServiceImpl<Student,Integer> implements StudentService {

    @Resource
    private StudentMapper studentMapper;


    /**
     * 根据id获取对象
     * @param id
     * @return Entity
     */
    public Student get(Integer id) throws DataAccessException {
        return studentMapper.selectByPrimaryKey(id);
    }

    /**
     * 取得所有Entity
     * @return List<E>
     */
    public List<Student> getAllList() throws DataAccessException {
        StudentExample example = new StudentExample();
        return studentMapper.selectByExample(example);
    }

    /**
     * 取得对象总数量
     * @return
     * @throws DataAccessException
     */
    public Integer getTotalCount() throws DataAccessException {
        StudentExample example = new StudentExample();
        return studentMapper.countByExample(example);
    }

    /**
     * 新增Entity
     * @param entity
     */
    public Student save(Student entity) throws DataAccessException {
        studentMapper.insertSelective(entity);
        return entity;
    }

    /**
     * 保存修改Entity
     * @param entity
     */
    public int update(Student entity) throws DataAccessException {
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(entity.getId());
        return studentMapper.updateByExample(entity, example);
    }

    /**
     * 新增或者保存修改Entity
     * @param entity
     */
    public Student saveOrUpdate(Student entity) throws DataAccessException {
        if(existById(entity.getId())){
            update(entity);
            return entity;
        }else{
            return save(entity);
        }
    }

    /**
     * 根据id删除
     * @param id
     */
    public void delete(Integer id) throws DataAccessException {
        studentMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据多个id删除
     * @param ids
     * @throws DataAccessException
     */
    public void deleteByIds(Integer[] ids) throws DataAccessException {
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        studentMapper.deleteByExample(example);
    }

    @Override
    public boolean existById(Integer id) throws DataAccessException {
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        return studentMapper.countByExample(example)>0?true:false;
    }


    public int del(Integer id) throws DataAccessException {
        return studentMapper.deleteByPrimaryKey(id);
    }
    public int delByIds(Integer[] ids) throws DataAccessException {
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        return studentMapper.deleteByExample(example);
    }

    public int bindClassinfo(Integer classid,List<Integer> studentList){
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(studentList);
        Student student = new Student();
        student.setClassid(classid);
        return studentMapper.updateByExampleSelective(student, example);
    }

    public List<Integer> getStudentidsByCourse(List<String> courseids){
        List<Student> studentcourses = studentMapper.getStudentidsByCourse(courseids);
        List<Integer> studentids=studentcourses.stream().map(Student::getId).collect(Collectors.toList());
        return studentids;
    }
}