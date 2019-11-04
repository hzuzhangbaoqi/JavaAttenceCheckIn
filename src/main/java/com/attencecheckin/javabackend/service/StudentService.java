
package com.attencecheckin.javabackend.service;

import com.attencecheckin.javabackend.entity.Student;

import java.util.List;

/**
* @Description: StudentService接口
* @author 
* @date 2019/10/17 02:38
*/
public interface StudentService extends BaseService<Student,Integer> {
    int bindClassinfo(Integer classid,List<Integer> studentList);

}