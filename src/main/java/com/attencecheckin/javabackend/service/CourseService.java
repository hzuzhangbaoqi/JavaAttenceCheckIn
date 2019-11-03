
package com.attencecheckin.javabackend.service;

import com.attencecheckin.javabackend.entity.Course;
import com.attencecheckin.javabackend.entity.Course;

import java.util.Date;

/**
* @Description: CourseService接口
* @author 
* @date 2019/10/17 09:42
*/
public interface CourseService extends BaseService<Course,Integer> {

    Course getCourseByTeacheridAndTime(Integer teacherId,String time);
}