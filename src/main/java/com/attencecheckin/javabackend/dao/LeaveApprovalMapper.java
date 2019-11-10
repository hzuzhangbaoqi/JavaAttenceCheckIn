package com.attencecheckin.javabackend.dao;

import com.attencecheckin.javabackend.entity.LeaveApproval;
import com.attencecheckin.javabackend.entity.LeaveApprovalExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface LeaveApprovalMapper  extends BaseDAO<LeaveApproval, Integer>{
    int countByExample(LeaveApprovalExample example);

    int deleteByExample(LeaveApprovalExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LeaveApproval record);

    int insertSelective(LeaveApproval record);

    List<LeaveApproval> selectByExample(LeaveApprovalExample example);

    LeaveApproval selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LeaveApproval record, @Param("example") LeaveApprovalExample example);

    int updateByExample(@Param("record") LeaveApproval record, @Param("example") LeaveApprovalExample example);

    int updateByPrimaryKeySelective(LeaveApproval record);

    int updateByPrimaryKey(LeaveApproval record);
    List<Map<String,Object>> showleaveCourseByTeacherid(@Param("teacherid") Integer teacherid);
    List<Map<String,Object>> getCourseByStudent(@Param("studentid") Integer studentid, @Param("statusList") List<Integer> statusList);
}