package com.attencecheckin.javabackend.service.impl;

import com.attencecheckin.javabackend.dao.ClassRoomMapper;
import com.attencecheckin.javabackend.entity.ClassRoom;
import com.attencecheckin.javabackend.entity.ClassRoomExample;
import com.attencecheckin.javabackend.service.ClassRoomService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
* @Description: ClassRoomService接口实现类
* @author 
* @date 2019/10/17 09:44
*/
@Service
public class ClassRoomServiceImpl extends AbstractBaseServiceImpl<ClassRoom,Integer> implements ClassRoomService {

    @Resource
    private ClassRoomMapper classroomMapper;
    /*
    @Autowired
    public void setBaseDAO(ClassRoomMapper classroomMapper) {
        super.setBaseDAO(classroomMapper);
    }*/

    /**
     * 根据id获取对象
     * @param id
     * @return Entity
     */
    public ClassRoom get(Integer id) throws DataAccessException {
        return classroomMapper.selectByPrimaryKey(id);
    }

    /**
     * 取得所有Entity
     * @return List<E>
     */
    public List<ClassRoom> getAllList() throws DataAccessException {
        ClassRoomExample example = new ClassRoomExample();
        return classroomMapper.selectByExample(example);
    }

    /**
     * 取得对象总数量
     * @return
     * @throws DataAccessException
     */
    public Integer getTotalCount() throws DataAccessException {
        ClassRoomExample example = new ClassRoomExample();
        return classroomMapper.countByExample(example);
    }

    /**
     * 新增Entity
     * @param entity
     */
    public ClassRoom save(ClassRoom entity) throws DataAccessException {
        classroomMapper.insertSelective(entity);
        return entity;
    }

    /**
     * 保存修改Entity
     * @param entity
     */
    public int update(ClassRoom entity) throws DataAccessException {
        ClassRoomExample example = new ClassRoomExample();
        ClassRoomExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(entity.getId());
        return classroomMapper.updateByExample(entity, example);
    }

    /**
     * 新增或者保存修改Entity
     * @param entity
     */
    public ClassRoom saveOrUpdate(ClassRoom entity) throws DataAccessException {
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
        classroomMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据多个id删除
     * @param ids
     * @throws DataAccessException
     */
    public void deleteByIds(Integer[] ids) throws DataAccessException {
        ClassRoomExample example = new ClassRoomExample();
        ClassRoomExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        classroomMapper.deleteByExample(example);
    }

    @Override
    public boolean existById(Integer id) throws DataAccessException {
        ClassRoomExample example = new ClassRoomExample();
        ClassRoomExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        return classroomMapper.countByExample(example)>0?true:false;
    }


    public int del(Integer id) throws DataAccessException {
        return classroomMapper.deleteByPrimaryKey(id);
    }
    public int delByIds(Integer[] ids) throws DataAccessException {
        ClassRoomExample example = new ClassRoomExample();
        ClassRoomExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        return classroomMapper.deleteByExample(example);
    }
}