package com.attencecheckin.javabackend.service.impl;

import com.attencecheckin.javabackend.controller.ClassRoomController;
import com.attencecheckin.javabackend.controller.TeacherController;
import com.attencecheckin.javabackend.dao.ClassInfoMapper;
import com.attencecheckin.javabackend.dao.CourseMapper;
import com.attencecheckin.javabackend.entity.*;
import com.attencecheckin.javabackend.service.ClassInfoService;
import com.attencecheckin.javabackend.service.ClassRoomService;
import com.attencecheckin.javabackend.service.CourseService;
import com.attencecheckin.javabackend.service.TeacherService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
* @Description: CourseService接口实现类
* @author 
* @date 2019/10/17 09:42
*/
@Service
public class CourseServiceImpl extends AbstractBaseServiceImpl<Course,Integer> implements CourseService {

    @Resource
    private CourseMapper courseMapper;


    @Resource
    private ClassInfoService classinfoService;
    @Resource
    private ClassRoomService classRoomService;
    @Resource
    private TeacherService teacherService;
    public void setBaseDAO(CourseMapper courseMapper) {
        super.setBaseDAO(courseMapper);
    }

    /**
     * 根据id获取对象
     * @param id
     * @return Entity
     */
    public Course get(Integer id) throws DataAccessException {
        return courseMapper.selectByPrimaryKey(id);
    }

    /**
     * 取得所有Entity
     * @return List<E>
     */
    public List<Course> getAllList() throws DataAccessException {
        CourseExample example = new CourseExample();
        return courseMapper.selectByExample(example);
    }

    /**
     * 取得对象总数量
     * @return
     * @throws DataAccessException
     */
    public Integer getTotalCount() throws DataAccessException {
        CourseExample example = new CourseExample();
        return courseMapper.countByExample(example);
    }

    /**
     * 新增Entity
     * @param entity
     */
    public Course save(Course entity) throws DataAccessException {
        courseMapper.insertSelective(entity);
        return entity;
    }

    /**
     * 保存修改Entity
     * @param entity
     */
    public int update(Course entity) throws DataAccessException {
        CourseExample example = new CourseExample();
        CourseExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(entity.getId());
        return courseMapper.updateByExampleSelective(entity, example);
    }

    /**
     * 新增或者保存修改Entity
     * @param entity
     */
    public Course saveOrUpdate(Course entity) throws DataAccessException {
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
        courseMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据多个id删除
     * @param ids
     * @throws DataAccessException
     */
    public void deleteByIds(Integer[] ids) throws DataAccessException {
        CourseExample example = new CourseExample();
        CourseExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        courseMapper.deleteByExample(example);
    }

    @Override
    public boolean existById(Integer id) throws DataAccessException {
        CourseExample example = new CourseExample();
        CourseExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        return courseMapper.countByExample(example)>0?true:false;
    }


    public int del(Integer id) throws DataAccessException {
        return courseMapper.deleteByPrimaryKey(id);
    }
    public int delByIds(Integer[] ids) throws DataAccessException {
        CourseExample example = new CourseExample();
        CourseExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        return courseMapper.deleteByExample(example);
    }

    public List<Course>getListByExample(CourseExample example){

        return null;
    }
    public Course getCourseByTeacheridAndTime(Integer teacherId,String time){
        List<Course> courses = courseMapper.selectCourseByTeacheridAndTime(teacherId,time);
        if(courses.size()<=0){
            return null;
        }
        return courses.get(0);
    }
    public Course getCourseByWeekAndJieci(Integer week,Integer jieci,Integer classid){
        CourseExample example = new CourseExample();
        CourseExample.Criteria criteria = example.createCriteria();
        criteria.andWeekEqualTo(week);
        criteria.andJieciEqualTo(jieci);
        criteria.andClassidEqualTo(classid);
        List<Course> courses = courseMapper.selectByExample(example);
        if(courses.size()>0){
            return courses.get(0);
        }
        return null;
    }
    public List<Map<String,Object>> getCourseByDate(String e){
        return courseMapper.getCourseByDate(e);
    }
    public List<Map<String,Object>> getCourseByStudentid(Integer classid){
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria = courseExample.createCriteria();
        criteria.andClassidEqualTo(classid);
        courseExample.setOrderByClause("week,jieci");
        List<Course> courses = courseMapper.selectByExample(courseExample);
        List<ClassInfo> classInfos = classinfoService.getAllList();
        List<ClassRoom> classRooms = classRoomService.getAllList();
        List<Teacher> teachers = teacherService.getAllList();

        Map<Integer, ClassInfo> classInfoMap = classInfos.stream().collect(Collectors.toMap(ClassInfo::getId,Function.identity()));
        Map<Integer, ClassRoom> classRoomMap = classRooms.stream().collect(Collectors.toMap(ClassRoom::getId,Function.identity()));
        Map<Integer, Teacher> teacherMap = teachers.stream().collect(Collectors.toMap(Teacher::getId,Function.identity()));
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        courses.stream().forEach(course->{
            try {
                Map<String,Object> item = BeanUtils.describe(course);
                item.put("classname", classInfoMap.get(course.getClassid())==null?"":classInfoMap.get(course.getClassid()).getClassname());
                item.put("classroomname", classRoomMap.get(course.getClassroomid())==null?"":classRoomMap.get(course.getClassroomid()).getClassroomname());
                item.put("teachername", teacherMap.get(course.getTeacherid())==null?"":teacherMap.get(course.getTeacherid()).getUsername());
                result.add(item);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        });
        return result;
    }
}