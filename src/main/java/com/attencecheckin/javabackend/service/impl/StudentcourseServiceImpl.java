package com.attencecheckin.javabackend.service.impl;

import com.attencecheckin.javabackend.dao.StudentcourseMapper;
import com.attencecheckin.javabackend.entity.Studentcourse;
import com.attencecheckin.javabackend.entity.StudentcourseExample;
import com.attencecheckin.javabackend.service.StudentcourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
* @Description: StudentcourseService接口实现类
* @author 
* @date 2019/11/03 13:43
*/
@Service
public class StudentcourseServiceImpl extends AbstractBaseServiceImpl<Studentcourse,Integer> implements StudentcourseService {

    @Resource
    private StudentcourseMapper studentcourseMapper;

    public void setBaseDAO(StudentcourseMapper studentcourseMapper) {
        super.setBaseDAO(studentcourseMapper);
    }

    /**
     * 根据id获取对象
     * @param id
     * @return Entity
     */
    public Studentcourse get(Integer id) throws DataAccessException {
        return studentcourseMapper.selectByPrimaryKey(id);
    }

    /**
     * 取得所有Entity
     * @return List<E>
     */
    public List<Studentcourse> getAllList() throws DataAccessException {
        StudentcourseExample example = new StudentcourseExample();
        return studentcourseMapper.selectByExample(example);
    }

    /**
     * 取得对象总数量
     * @return
     * @throws DataAccessException
     */
    public Integer getTotalCount() throws DataAccessException {
        StudentcourseExample example = new StudentcourseExample();
        return studentcourseMapper.countByExample(example);
    }

    /**
     * 新增Entity
     * @param entity
     */
    public Studentcourse save(Studentcourse entity) throws DataAccessException {
        studentcourseMapper.insertSelective(entity);
        return entity;
    }

    /**
     * 保存修改Entity
     * @param entity
     */
    public int update(Studentcourse entity) throws DataAccessException {
        StudentcourseExample example = new StudentcourseExample();
        StudentcourseExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(entity.getId());
        return studentcourseMapper.updateByExample(entity, example);
    }

    /**
     * 新增或者保存修改Entity
     * @param entity
     */
    public Studentcourse saveOrUpdate(Studentcourse entity) throws DataAccessException {
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
        studentcourseMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据多个id删除
     * @param ids
     * @throws DataAccessException
     */
    public void deleteByIds(Integer[] ids) throws DataAccessException {
        StudentcourseExample example = new StudentcourseExample();
        StudentcourseExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        studentcourseMapper.deleteByExample(example);
    }

    @Override
    public boolean existById(Integer id) throws DataAccessException {
        StudentcourseExample example = new StudentcourseExample();
        StudentcourseExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        return studentcourseMapper.countByExample(example)>0?true:false;
    }


    public int del(Integer id) throws DataAccessException {
        return studentcourseMapper.deleteByPrimaryKey(id);
    }
    public int delByIds(Integer[] ids) throws DataAccessException {
        StudentcourseExample example = new StudentcourseExample();
        StudentcourseExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        return studentcourseMapper.deleteByExample(example);
    }

    public List<Integer> getStudentidsByCourse(Integer courseid){
        StudentcourseExample example = new StudentcourseExample();
        StudentcourseExample.Criteria criteria = example.createCriteria();
        criteria.andCourseidEqualTo(courseid);
        List<Studentcourse> studentcourses = studentcourseMapper.selectByExample(example);
        List<Integer> studentids=studentcourses.stream().map(Studentcourse::getStudentid).collect(Collectors.toList());
        return studentids;
    }
}