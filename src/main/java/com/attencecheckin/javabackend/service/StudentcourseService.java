
package com.attencecheckin.javabackend.service;

import com.attencecheckin.javabackend.entity.Studentcourse;

import java.util.List;

/**
* @Description: StudentcourseService接口
* @author 
* @date 2019/11/03 13:43
*/
public interface StudentcourseService extends BaseService<Studentcourse,Integer> {

    List<Integer> getStudentidsByCourse(Integer courseid);
}