package com.attencecheckin.javabackend.service.impl;

import com.attencecheckin.javabackend.dao.SignInMapper;
import com.attencecheckin.javabackend.entity.SignIn;
import com.attencecheckin.javabackend.entity.SignInExample;
import com.attencecheckin.javabackend.service.SigninService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: SigninService接口实现类
 * @author
 * @date 2019/10/17 09:30
 */
@Service
public class SigninServiceImpl extends AbstractBaseServiceImpl<SignIn,Integer> implements SigninService {

    @Resource
    private SignInMapper signInMapper;
    /*
    @Autowired
    public void setBaseDAO(SigninMapper signinMapper) {
        super.setBaseDAO(signinMapper);
    }*/

    /**
     * 根据id获取对象
     * @param id
     * @return Entity
     */
    public SignIn get(Integer id) throws DataAccessException {
        return signInMapper.selectByPrimaryKey(id);
    }

    /**
     * 取得所有Entity
     * @return List<E>
     */
    public List<SignIn> getAllList() throws DataAccessException {
        SignInExample example = new SignInExample();
        return signInMapper.selectByExample(example);
    }

    /**
     * 取得对象总数量
     * @return
     * @throws DataAccessException
     */
    public Integer getTotalCount() throws DataAccessException {
        SignInExample example = new SignInExample();
        return signInMapper.countByExample(example);
    }

    /**
     * 新增Entity
     * @param entity
     */
    public SignIn save(SignIn entity) throws DataAccessException {
        signInMapper.insertSelective(entity);
        return entity;
    }

    /**
     * 保存修改Entity
     * @param entity
     */
    public int update(SignIn entity) throws DataAccessException {
        SignInExample example = new SignInExample();
        SignInExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(entity.getId());
        return signInMapper.updateByExample(entity, example);
    }

    /**
     * 新增或者保存修改Entity
     * @param entity
     */
    public SignIn saveOrUpdate(SignIn entity) throws DataAccessException {
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
        signInMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据多个id删除
     * @param ids
     * @throws DataAccessException
     */
    public void deleteByIds(Integer[] ids) throws DataAccessException {
        SignInExample example = new SignInExample();
        SignInExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        signInMapper.deleteByExample(example);
    }

    @Override
    public boolean existById(Integer id) throws DataAccessException {
        SignInExample example = new SignInExample();
        SignInExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        return signInMapper.countByExample(example)>0?true:false;
    }


    public int del(Integer id) throws DataAccessException {
        return signInMapper.deleteByPrimaryKey(id);
    }
    public int delByIds(Integer[] ids) throws DataAccessException {
        SignInExample example = new SignInExample();
        SignInExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        return signInMapper.deleteByExample(example);
    }
}