
package com.attencecheckin.javabackend.service;

import com.attencecheckin.javabackend.entity.ClassInfo;

import java.util.List;
import java.util.Map;

/**
* @Description: ClassInfoService接口
* @author 
* @date 2019/10/17 10:30
*/
public interface ClassInfoService extends BaseService<ClassInfo,Integer> {

    List<Map<String,Object>> selectKV();
}