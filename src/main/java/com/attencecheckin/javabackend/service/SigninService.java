
package com.attencecheckin.javabackend.service;

import com.attencecheckin.javabackend.entity.SignIn;
import com.attencecheckin.javabackend.entity.SignIn;

import java.util.List;
import java.util.Map;

/**
* @Description: SigninService接口
* @author 
* @date 2019/10/17 09:30
*/
public interface SigninService extends BaseService<SignIn,Integer> {
    List<SignIn> selectSigninBycourseId(Integer courseid, Integer type);
    int studentSignin(Integer studentid,Integer courseid,String location,Integer doubt);
    List<SignIn> showSignin(Integer courseid,String time,Integer signtype);
    List<Map<String,Object>> getAbnormal(List<Integer> users,List<Integer> status);

    List<Map<String,Object>>getAttendanceStatistics(String signtimeStart ,String signtimeEnd,Integer studentid);
}