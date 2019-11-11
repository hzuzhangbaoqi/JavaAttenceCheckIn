
package com.attencecheckin.javabackend.service;

import com.attencecheckin.javabackend.entity.Course;
import com.attencecheckin.javabackend.entity.Course;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @Description: CourseService接口
* @author 
* @date 2019/10/17 09:42
*/
public interface CourseService extends BaseService<Course,Integer> {

    Course getCourseByTeacheridAndTime(Integer teacherId,String time);
    List<Map<String,Object>> getCourseByDate(String week);
    Course getCourseByWeekAndJieci(Integer week,Integer jieci,Integer classid);
    List<Map<String,Object>> getCourseByStudentid(Integer classid);
    List<Map<String,Object>> getCourseByTeacherid(Integer teacherid);
}