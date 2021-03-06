package com.attencecheckin.javabackend.service.impl;

import com.attencecheckin.javabackend.dao.ClassInfoMapper;
import com.attencecheckin.javabackend.dao.StudentMapper;
import com.attencecheckin.javabackend.dao.TeacherMapper;
import com.attencecheckin.javabackend.entity.*;
import com.attencecheckin.javabackend.service.LoginInfoLogService;
import com.attencecheckin.javabackend.dao.LoginInfoLogMapper;
import com.attencecheckin.javabackend.service.LoginInfoLogService;
import com.attencecheckin.javabackend.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
* @Description: LoginInfoLogService接口实现类
* @author 
* @date 2019/10/17 09:38
*/
@Service
public class LoginInfoLogServiceImpl extends AbstractBaseServiceImpl<LoginInfoLog,Integer> implements LoginInfoLogService {

    @Resource
    private LoginInfoLogMapper logininfologMapper;
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private TeacherMapper teacherMapper;

    @Resource
    private ClassInfoMapper classinfoMapper;
    /*
    @Autowired
    public void setBaseDAO(LoginInfoLogMapper logininfologMapper) {
        super.setBaseDAO(logininfologMapper);
    }*/

    /**
     * 根据id获取对象
     * @param id
     * @return Entity
     */
    public LoginInfoLog get(Integer id) throws DataAccessException {
        return logininfologMapper.selectByPrimaryKey(id);
    }

    /**
     * 取得所有Entity
     * @return List<E>
     */
    public List<LoginInfoLog> getAllList() throws DataAccessException {
        LoginInfoLogExample example = new LoginInfoLogExample();
        return logininfologMapper.selectByExample(example);
    }

    /**
     * 取得对象总数量
     * @return
     * @throws DataAccessException
     */
    public Integer getTotalCount() throws DataAccessException {
        LoginInfoLogExample example = new LoginInfoLogExample();
        return logininfologMapper.countByExample(example);
    }

    /**
     * 新增Entity
     * @param entity
     */
    public LoginInfoLog save(LoginInfoLog entity) throws DataAccessException {
        logininfologMapper.insertSelective(entity);
        return entity;
    }

    /**
     * 保存修改Entity
     * @param entity
     */
    public int update(LoginInfoLog entity) throws DataAccessException {
        LoginInfoLogExample example = new LoginInfoLogExample();
        LoginInfoLogExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(entity.getId());
        return logininfologMapper.updateByExample(entity, example);
    }

    /**
     * 新增或者保存修改Entity
     * @param entity
     */
    public LoginInfoLog saveOrUpdate(LoginInfoLog entity) throws DataAccessException {
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
        logininfologMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据多个id删除
     * @param ids
     * @throws DataAccessException
     */
    public void deleteByIds(Integer[] ids) throws DataAccessException {
        LoginInfoLogExample example = new LoginInfoLogExample();
        LoginInfoLogExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        logininfologMapper.deleteByExample(example);
    }

    @Override
    public boolean existById(Integer id) throws DataAccessException {
        LoginInfoLogExample example = new LoginInfoLogExample();
        LoginInfoLogExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        return logininfologMapper.countByExample(example)>0?true:false;
    }


    public int del(Integer id) throws DataAccessException {
        return logininfologMapper.deleteByPrimaryKey(id);
    }
    public int delByIds(Integer[] ids) throws DataAccessException {
        LoginInfoLogExample example = new LoginInfoLogExample();
        LoginInfoLogExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        return logininfologMapper.deleteByExample(example);
    }
    public Map<String,Object> getStatistical(){
        //学生
        int studentCount = studentMapper.countByExample(new StudentExample());
        //教师
        int teacherCount = teacherMapper.countByExample(new TeacherExample());
        //班级数
        int classinfoCount = classinfoMapper.countByExample(new ClassInfoExample());

        Map<String,Object> result = new HashMap<String,Object>();
        result.put("studentCount",studentCount);
        result.put("teacherCount",teacherCount);
        result.put("classinfoCount",classinfoCount);
        return  result;
    }

    public Map<String,Object> getLogininfoByWeek(){
        List<Map<String, Object>> logininfoByWeek = logininfologMapper.getLogininfoByWeek();
        Map<String,Object> result = new LinkedHashMap<String,Object>();
        logininfoByWeek.forEach(item->{
            result.put(""+item.get("logintime"), item.get("count"));
        });
        return result;
    }
}