package com.attencecheckin.javabackend.service.impl;

import com.attencecheckin.javabackend.dao.ClassInfoMapper;
import com.attencecheckin.javabackend.entity.ClassInfo;
import com.attencecheckin.javabackend.entity.ClassInfoExample;
import com.attencecheckin.javabackend.service.ClassInfoService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
* @Description: ClassInfoService接口实现类
* @author 
* @date 2019/10/17 10:30
*/
@Service
public class ClassInfoServiceImpl extends AbstractBaseServiceImpl<ClassInfo,Integer> implements ClassInfoService {

    @Resource
    private ClassInfoMapper classinfoMapper;
    /*
    @Autowired
    public void setBaseDAO(ClassInfoMapper classinfoMapper) {
        super.setBaseDAO(classinfoMapper);
    }*/

    /**
     * 根据id获取对象
     * @param id
     * @return Entity
     */
    public ClassInfo get(Integer id) throws DataAccessException {
        return classinfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 取得所有Entity
     * @return List<E>
     */
    public List<ClassInfo> getAllList() throws DataAccessException {
        ClassInfoExample example = new ClassInfoExample();
        return classinfoMapper.selectByExample(example);
    }

    /**
     * 取得对象总数量
     * @return
     * @throws DataAccessException
     */
    public Integer getTotalCount() throws DataAccessException {
        ClassInfoExample example = new ClassInfoExample();
        return classinfoMapper.countByExample(example);
    }

    /**
     * 新增Entity
     * @param entity
     */
    public ClassInfo save(ClassInfo entity) throws DataAccessException {
        classinfoMapper.insertSelective(entity);
        return entity;
    }

    /**
     * 保存修改Entity
     * @param entity
     */
    public int update(ClassInfo entity) throws DataAccessException {
        ClassInfoExample example = new ClassInfoExample();
        ClassInfoExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(entity.getId());
        return classinfoMapper.updateByExample(entity, example);
    }

    /**
     * 新增或者保存修改Entity
     * @param entity
     */
    public ClassInfo saveOrUpdate(ClassInfo entity) throws DataAccessException {
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
        classinfoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据多个id删除
     * @param ids
     * @throws DataAccessException
     */
    public void deleteByIds(Integer[] ids) throws DataAccessException {
        ClassInfoExample example = new ClassInfoExample();
        ClassInfoExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        classinfoMapper.deleteByExample(example);
    }

    @Override
    public boolean existById(Integer id) throws DataAccessException {
        ClassInfoExample example = new ClassInfoExample();
        ClassInfoExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        return classinfoMapper.countByExample(example)>0?true:false;
    }


    public int del(Integer id) throws DataAccessException {
        return classinfoMapper.deleteByPrimaryKey(id);
    }
    public int delByIds(Integer[] ids) throws DataAccessException {
        ClassInfoExample example = new ClassInfoExample();
        ClassInfoExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        return classinfoMapper.deleteByExample(example);
    }
}