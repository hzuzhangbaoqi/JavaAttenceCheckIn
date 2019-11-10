
package com.attencecheckin.javabackend.service;

import com.attencecheckin.javabackend.entity.LoginInfoLog;

import java.util.Map;

/**
* @Description: LoginInfoLogService接口
* @author 
* @date 2019/10/17 09:38
*/
public interface LoginInfoLogService extends BaseService<LoginInfoLog,Integer> {
    Map<String,Object> getStatistical();
    Map<String,Object> getLogininfoByWeek();

}