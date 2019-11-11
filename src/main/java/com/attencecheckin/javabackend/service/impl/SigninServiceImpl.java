package com.attencecheckin.javabackend.service.impl;

import com.attencecheckin.javabackend.dao.SignInMapper;
import com.attencecheckin.javabackend.entity.SignIn;
import com.attencecheckin.javabackend.entity.SignInExample;
import com.attencecheckin.javabackend.service.SigninService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.client.utils.DateUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
    public List<SignIn> selectSigninBycourseId(Integer courseid,Integer type){
        SignInExample example = new SignInExample();
        SignInExample.Criteria criteria = example.createCriteria();
        criteria.andCourseidEqualTo(courseid);
        criteria.andSigntypeEqualTo(type);
        Date date1 = DateUtils.parseDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd") + " 00:00:00", new String[]{"yyyy-MM-dd hh:mm:ss"});
        criteria.andSigntimeGreaterThanOrEqualTo(date1);
        Date date2 = DateUtils.parseDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd") + " 23:59:59", new String[]{"yyyy-MM-dd hh:mm:ss"});
        criteria.andSigntimeLessThanOrEqualTo(date2);
        return signInMapper.selectByExample(example);
    }

    public int studentSignin(Integer studentid,Integer courseid,String location,Integer doubt) throws DataAccessException {
        //获取教师签到的位置
        SignInExample example = new SignInExample();
        SignInExample.Criteria criteria = example.createCriteria();
        criteria.andStudentidEqualTo(studentid);
        criteria.andCourseidEqualTo(courseid);
        Date date1 = DateUtils.parseDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd") + " 00:00:00", new String[]{"yyyy-MM-dd hh:mm:ss"});
        criteria.andSigntimeGreaterThanOrEqualTo(date1);
        Date date2 = DateUtils.parseDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd") + " 23:59:59", new String[]{"yyyy-MM-dd hh:mm:ss"});
        criteria.andSigntimeLessThanOrEqualTo(date2);
        SignIn signIn = new SignIn();
        signIn.setSigntime(new Date());
        signIn.setStatus(1);
        signIn.setSuslocation(location);
        signIn.setSuslocationstatus(doubt);
        return signInMapper.updateByExampleSelective(signIn, example);
    }

    public List<SignIn> showSignin(Integer courseid,String time,Integer signtype) throws DataAccessException {
        return signInMapper.showSignin(courseid,time,signtype);
    }
    public List<Map<String,Object>> getAbnormal(List<Integer> users,List<Integer> status) throws DataAccessException {
        return signInMapper.getAbnormal(users,status);
    }

    public  List<Map<String,Object>>getAttendanceStatistics(String signtimeStart , String signtimeEnd, Integer studentid){
        List<Map<String, Object>> attendanceStatistics = signInMapper.getAttendanceStatistics(signtimeStart, signtimeEnd, studentid);

        Map<Integer,Map<String,Object>> map = new HashMap<Integer,Map<String,Object>>();
        attendanceStatistics.forEach(item->{
            Integer sid = (Integer) item.get("studentid");
            Integer status = (Integer) item.get("status");
            Long count = (Long) item.get("count");

            if(null == map.get(sid)){
                HashMap<String, Object> attendanceMap = new HashMap<>();
                attendanceMap.put("studentid", sid);
                attendanceMap.put("studentname", item.get("username"));
                if(status==0){
                    attendanceMap.put("notsignin", count);
                }else if(status==1){
                    attendanceMap.put("issignedin", count);
                }else if(status==2){
                    attendanceMap.put("leavesign", count);
                }else if(status==3){
                    attendanceMap.put("late", count);
                }else if(status==4){
                    attendanceMap.put("four", count);
                }
                map.put(sid, attendanceMap);
            }else{
                HashMap<String, Object> attendanceMap = (HashMap<String, Object>) map.get(sid);
                if(status==0){
                    attendanceMap.put("notsignin", count);
                }else if(status==1){
                    attendanceMap.put("issignedin", count);
                }else if(status==2){
                    attendanceMap.put("leavesign", count);
                }else if(status==3){
                    attendanceMap.put("late", count);
                }else if(status==4){
                    attendanceMap.put("four", count);
                }
            }
        });

        List<Map<String,Object>> resule = new ArrayList<Map<String,Object>>();
        for(Map.Entry<Integer,Map<String,Object>> item : map.entrySet())
            resule.add(item.getValue());
        return resule;
    }

}