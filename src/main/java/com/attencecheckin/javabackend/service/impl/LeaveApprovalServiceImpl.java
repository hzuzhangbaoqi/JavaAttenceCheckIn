package com.attencecheckin.javabackend.service.impl;

import com.attencecheckin.javabackend.dao.LeaveApprovalMapper;
import com.attencecheckin.javabackend.entity.LeaveApproval;
import com.attencecheckin.javabackend.entity.LeaveApprovalExample;
import com.attencecheckin.javabackend.service.LeaveApprovalService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
* @Description: LeaveApprovalService接口实现类
* @author 
* @date 2019/10/17 09:41
*/
@Service
public class LeaveApprovalServiceImpl extends AbstractBaseServiceImpl<LeaveApproval,Integer> implements LeaveApprovalService {

    @Resource
    private LeaveApprovalMapper leaveapprovalMapper;
    /*
    @Autowired
    public void setBaseDAO(LeaveApprovalMapper leaveapprovalMapper) {
        super.setBaseDAO(leaveapprovalMapper);
    }*/

    /**
     * 根据id获取对象
     * @param id
     * @return Entity
     */
    public LeaveApproval get(Integer id) throws DataAccessException {
        return leaveapprovalMapper.selectByPrimaryKey(id);
    }

    /**
     * 取得所有Entity
     * @return List<E>
     */
    public List<LeaveApproval> getAllList() throws DataAccessException {
        LeaveApprovalExample example = new LeaveApprovalExample();
        return leaveapprovalMapper.selectByExample(example);
    }

    /**
     * 取得对象总数量
     * @return
     * @throws DataAccessException
     */
    public Integer getTotalCount() throws DataAccessException {
        LeaveApprovalExample example = new LeaveApprovalExample();
        return leaveapprovalMapper.countByExample(example);
    }

    /**
     * 新增Entity
     * @param entity
     */
    public LeaveApproval save(LeaveApproval entity) throws DataAccessException {
        leaveapprovalMapper.insertSelective(entity);
        return entity;
    }
    public LeaveApproval findByDateAndcourseid(Integer studentid,String date,Integer courseid) {
        LeaveApprovalExample example = new LeaveApprovalExample();
        LeaveApprovalExample.Criteria criteria = example.createCriteria();
        criteria.andLeavedateEqualTo(date);
        criteria.andCourseidEqualTo(courseid);
        criteria.andStudentidEqualTo(studentid);
        List<LeaveApproval> leaveApprovals = leaveapprovalMapper.selectByExample(example);
        if(leaveApprovals.size()>0){
            return  leaveApprovals.get(0);
        }
        return null;
    }
    /**
     * 保存修改Entity
     * @param entity
     */
    public int update(LeaveApproval entity) throws DataAccessException {
        LeaveApprovalExample example = new LeaveApprovalExample();
        LeaveApprovalExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(entity.getId());
        return leaveapprovalMapper.updateByExample(entity, example);
    }

    /**
     * 新增或者保存修改Entity
     * @param entity
     */
    public LeaveApproval saveOrUpdate(LeaveApproval entity) throws DataAccessException {
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
        leaveapprovalMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据多个id删除
     * @param ids
     * @throws DataAccessException
     */
    public void deleteByIds(Integer[] ids) throws DataAccessException {
        LeaveApprovalExample example = new LeaveApprovalExample();
        LeaveApprovalExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        leaveapprovalMapper.deleteByExample(example);
    }

    @Override
    public boolean existById(Integer id) throws DataAccessException {
        LeaveApprovalExample example = new LeaveApprovalExample();
        LeaveApprovalExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        return leaveapprovalMapper.countByExample(example)>0?true:false;
    }


    public int del(Integer id) throws DataAccessException {
        return leaveapprovalMapper.deleteByPrimaryKey(id);
    }
    public int delByIds(Integer[] ids) throws DataAccessException {
        LeaveApprovalExample example = new LeaveApprovalExample();
        LeaveApprovalExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        return leaveapprovalMapper.deleteByExample(example);
    }

    public List<Map<String,Object>> showleaveCourseByTeacherid(Integer teacherid){
        return leaveapprovalMapper.showleaveCourseByTeacherid(teacherid);
    }

    public int agreeleaveCourseByTeacherid(Integer teacher,List<Integer> ids,Integer isAgree){
        LeaveApprovalExample example = new LeaveApprovalExample();
        LeaveApprovalExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        LeaveApproval leaveApproval = new LeaveApproval();
        leaveApproval.setStatus(isAgree);
        return leaveapprovalMapper.updateByExampleSelective(leaveApproval, example);
    }
    public List<Map<String,Object>>  getCourseByStudent(Integer studentid,List<Integer> status){

        List<Map<String,Object>> leaveApprovals = leaveapprovalMapper.getCourseByStudent(studentid, status);;
        return leaveApprovals;
    }
}