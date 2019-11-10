
package com.attencecheckin.javabackend.service;

import com.attencecheckin.javabackend.entity.LeaveApproval;

import java.util.List;
import java.util.Map;

/**
* @Description: LeaveApprovalService接口
* @author 
* @date 2019/10/17 09:41
*/
public interface LeaveApprovalService extends BaseService<LeaveApproval,Integer> {

    LeaveApproval findByDateAndcourseid(Integer studentid,String date,Integer courseid);
    List<Map<String,Object>> showleaveCourseByTeacherid(Integer teacherid);
    int agreeleaveCourseByTeacherid(Integer teacherid,List<Integer> ids,Integer isAgree);
    List<Map<String,Object>> getCourseByStudent(Integer studentid, List<Integer> status);
}