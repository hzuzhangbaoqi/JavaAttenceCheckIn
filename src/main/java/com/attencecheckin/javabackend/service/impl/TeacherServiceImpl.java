package com.attencecheckin.javabackend.service.impl;

import com.attencecheckin.javabackend.dao.TeacherMapper;
import com.attencecheckin.javabackend.entity.Teacher;
import com.attencecheckin.javabackend.entity.TeacherExample;
import com.attencecheckin.javabackend.service.TeacherService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
* @Description: TeacherService接口实现类
* @author 
* @date 2019/10/17 02:47
*/
@Service
public class TeacherServiceImpl extends AbstractBaseServiceImpl<Teacher,Integer> implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;
    /*
    @Autowired
    public void setBaseDAO(TeacherMapper teacherMapper) {
        super.setBaseDAO(teacherMapper);
    }*/

    /**
     * 根据id获取对象
     * @param id
     * @return Entity
     */
    public Teacher get(Integer id) throws DataAccessException {
        return teacherMapper.selectByPrimaryKey(id);
    }

    /**
     * 取得所有Entity
     * @return List<E>
     */
    public List<Teacher> getAllList() throws DataAccessException {
        TeacherExample example = new TeacherExample();
        List<Teacher> list = teacherMapper.selectByExample(example);
        return list;
    }

    /**
     * 取得对象总数量
     * @return
     * @throws DataAccessException
     */
    public Integer getTotalCount() throws DataAccessException {
        TeacherExample example = new TeacherExample();
        return teacherMapper.countByExample(example);
    }

    /**
     * 新增Entity
     * @param entity
     */
    public Teacher save(Teacher entity) throws DataAccessException {
        teacherMapper.insertSelective(entity);
        return entity;
    }

    /**
     * 保存修改Entity
     * @param entity
     */
    public int update(Teacher entity) throws DataAccessException {
        TeacherExample example = new TeacherExample();
        TeacherExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(entity.getId());
        return teacherMapper.updateByExample(entity, example);
    }

    /**
     * 新增或者保存修改Entity
     * @param entity
     */
    public Teacher saveOrUpdate(Teacher entity) throws DataAccessException {
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
        teacherMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据多个id删除
     * @param ids
     * @throws DataAccessException
     */
    public void deleteByIds(Integer[] ids) throws DataAccessException {
        TeacherExample example = new TeacherExample();
        TeacherExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        teacherMapper.deleteByExample(example);
    }

    @Override
    public boolean existById(Integer id) throws DataAccessException {
        TeacherExample example = new TeacherExample();
        TeacherExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        return teacherMapper.countByExample(example)>0?true:false;
    }


    public int del(Integer id) throws DataAccessException {
        return teacherMapper.deleteByPrimaryKey(id);
    }
    public int delByIds(Integer[] ids) throws DataAccessException {
        TeacherExample example = new TeacherExample();
        TeacherExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        return teacherMapper.deleteByExample(example);
    }
}