
package com.attencecheckin.javabackend.controller;

import com.attencecheckin.javabackend.common.JsonResult;
import com.attencecheckin.javabackend.common.enumer.ResultEnum;
import com.attencecheckin.javabackend.entity.Course;
import com.attencecheckin.javabackend.entity.CourseExample;
import com.attencecheckin.javabackend.entity.Student;
import com.attencecheckin.javabackend.entity.Teacher;
import com.attencecheckin.javabackend.service.CourseService;
import com.attencecheckin.javabackend.service.StudentService;
import com.attencecheckin.javabackend.service.TeacherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
* @Description: CourseController类
* @author 
* @date 2019/10/17 09:42
*/
@RestController
@RequestMapping("/course")
@Api(value = "course 在线接口文档")
public class CourseController {

    @Resource
    private CourseService courseService;
    @Resource
    private StudentService studentService;
    @Resource
    private TeacherService teacherService;

    @RequestMapping("/insert")
    @ApiOperation(value = "insert", notes = "增加一条数据")
    public JsonResult<Integer> insert(Course course) throws Exception{
        /*course.setId(ApplicationUtils.getUUID());*/
        if(1==course.getJieci()){
            course.setCoursestarttime(DateUtils.parseDate("08:00:00", new String[]{"hh:mm:ss"}));
            course.setCourseendtime(DateUtils.parseDate("09:45:00", new String[]{"hh:mm:ss"}));
        }else if(2==course.getJieci()){
            course.setCoursestarttime(DateUtils.parseDate("10:00:00", new String[]{"hh:mm:ss"}));
            course.setCourseendtime(DateUtils.parseDate("11:45:00", new String[]{"hh:mm:ss"}));
        }else if(3==course.getJieci()){
            course.setCoursestarttime(DateUtils.parseDate("14:00:00", new String[]{"hh:mm:ss"}));
            course.setCourseendtime(DateUtils.parseDate("15:45:00", new String[]{"hh:mm:ss"}));
        }else if(4==course.getJieci()){
            course.setCoursestarttime(DateUtils.parseDate("16:00:00", new String[]{"hh:mm:ss"}));
            course.setCourseendtime(DateUtils.parseDate("17:45:00", new String[]{"hh:mm:ss"}));
        }else if(5==course.getJieci()){
            course.setCoursestarttime(DateUtils.parseDate("18:00:00", new String[]{"hh:mm:ss"}));
            course.setCourseendtime(DateUtils.parseDate("23:45:00", new String[]{"hh:mm:ss"}));
        }
        courseService.save(course);
        return new JsonResult<Integer>(1);
    }
    @RequestMapping("/deleteById")
    @ApiOperation(value = "deleteById", notes = "根据id删除数据")
    public JsonResult<Integer> deleteById(@RequestParam @ApiParam(name = "id", value = "id", required = true) Integer id) throws Exception {
        Integer state = courseService.del(id);
        return new JsonResult<Integer>(state);
    }

    @RequestMapping("/update")
    @ApiOperation(value = "update", notes = "更新数据")
    public JsonResult<Integer> update(Course course) throws Exception {

        if(course.getJieci()!=null){
            if(1==course.getJieci()){
                course.setCoursestarttime(DateUtils.parseDate("08:00:00", new String[]{"hh:mm:ss"}));
                course.setCourseendtime(DateUtils.parseDate("09:45:00", new String[]{"hh:mm:ss"}));
            }else if(2==course.getJieci()){
                course.setCoursestarttime(DateUtils.parseDate("10:00:00", new String[]{"hh:mm:ss"}));
                course.setCourseendtime(DateUtils.parseDate("11:45:00", new String[]{"hh:mm:ss"}));
            }else if(3==course.getJieci()){
                course.setCoursestarttime(DateUtils.parseDate("14:00:00", new String[]{"hh:mm:ss"}));
                course.setCourseendtime(DateUtils.parseDate("15:45:00", new String[]{"hh:mm:ss"}));
            }else if(4==course.getJieci()){
                course.setCoursestarttime(DateUtils.parseDate("16:00:00", new String[]{"hh:mm:ss"}));
                course.setCourseendtime(DateUtils.parseDate("17:45:00", new String[]{"hh:mm:ss"}));
            }else if(5==course.getJieci()){
                course.setCoursestarttime(DateUtils.parseDate("18:00:00", new String[]{"hh:mm:ss"}));
                course.setCourseendtime(DateUtils.parseDate("23:45:00", new String[]{"hh:mm:ss"}));
            }
        }
        Integer state = courseService.update(course);
        return new JsonResult<Integer>(state);
    }

    @RequestMapping("/selectById")
    @ApiOperation(value = "selectById", notes = "更加id查询")
    public JsonResult<Course> selectById(@RequestParam @ApiParam(name = "id", value = "id", required = true) Integer id) throws Exception {
        Course course = courseService.get(id);
        return new JsonResult<Course>(course);
    }

    /**
     * @Description: 分页查询
     * @param page 页码
     * @param size 每页条数
     * @Reutrn JsonResult<PageInfo<Course>>
     */
    @RequestMapping("/list")
    @ApiOperation(value = "list", notes = "批量查询")
    public PageInfo<Map<String,Object>> list(@RequestParam(defaultValue = "0")@ApiParam(name = "page", value = "页数", required = true) Integer page,
                                 @RequestParam(defaultValue = "100")@ApiParam(name = "size", value = "行数", required = true) Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<Map<String,Object>> list = courseService.getListByExample(new CourseExample());
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(list);
        return pageInfo;
    }
    @RequestMapping("/getCourseByDate")
    @ApiOperation(value = "getCourseByDate", notes = "根据日期获取当天的课程")
    public List<Map<String,Object>> getCourseByDate(String date) throws ParseException {

        Date time = DateUtils.parseDate(date, "yyyy-MM-dd");
        String e = DateFormatUtils.format(time, "E");
        if(e.equals("星期一")){ e="1";}
        else if(e.equals("星期二")){ e="2";}
        else if(e.equals("星期三")){ e="3";}
        else if(e.equals("星期四")){ e="4";}
        else if(e.equals("星期五")){ e="5";}
        else if(e.equals("星期六")){ e="6";}
        else if(e.equals("星期日")){ e="0";}
        List<Map<String, Object>> courseByDate = courseService.getCourseByDate(e);
        return courseByDate;
    }
    @RequestMapping("/getCourseByStudentid")
    @ApiOperation(value = "getCourseByStudentid", notes = "根据日期获取当天的课程")
    public JsonResult<Map<String,Object>> getCourseByStudentid(String studentid) throws ParseException {
        Student student = studentService.get(Integer.parseInt(studentid));
        if(student == null||student.getClassid()==null){
            JsonResult mapJsonResult = new JsonResult<>(ResultEnum.FAIL);
            return mapJsonResult;
        }
        List<Map<String, Object>> courseByDate = courseService.getCourseByStudentid(student.getClassid());

        String[][] cycle = new String[][]{{"","","","","","",""},{"","","","","","",""},{"","","","","","",""},{"","","","","","",""},{"","","","","","",""}};
        for(Map<String, Object> item : courseByDate){
            Object jieci = item.get("jieci");
            Object week =  item.get("week");
            Object coursename = item.get("coursename");
            System.out.println(""+(Integer.parseInt(jieci+"")-1) + "   " + (Integer.parseInt(week+"")-1) + ": "+String.valueOf(coursename));
            cycle[Integer.parseInt(jieci+"")-1][Integer.parseInt(week+"")-1] = String.valueOf(coursename);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("cycle", cycle);
        map.put("data", courseByDate);

        JsonResult result = new JsonResult<>(ResultEnum.NORMAL.val(),"查询成功");
        result.setData(map);
        return result;
    }
    @RequestMapping("/getCourseByTeacherid")
    @ApiOperation(value = "getCourseByTeacherid", notes = "课程")
    public JsonResult<Map<String,Object>> getCourseByTeacherid(String teacherid) throws ParseException {
        Teacher teacher = teacherService.get(Integer.parseInt(teacherid));
        if(teacher == null){JsonResult mapJsonResult = new JsonResult<>(ResultEnum.FAIL);
            return mapJsonResult;}
        List<Map<String, Object>> courseByDate = courseService.getCourseByTeacherid(teacher.getId());
        String[][] cycle = new String[][]{{"","","","","","",""},{"","","","","","",""},{"","","","","","",""},{"","","","","","",""},{"","","","","","",""}};
        for(Map<String, Object> item : courseByDate){
            Object jieci = item.get("jieci");
            Object week =  item.get("week");
            Object coursename = item.get("coursename");
            System.out.println(""+(Integer.parseInt(jieci+"")-1) + "   " + (Integer.parseInt(week+"")-1) + ": "+String.valueOf(coursename));
            cycle[Integer.parseInt(jieci+"")-1][Integer.parseInt(week+"")-1] = String.valueOf(coursename);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("cycle", cycle);
        map.put("data", courseByDate);

        JsonResult result = new JsonResult<>(ResultEnum.NORMAL.val(),"查询成功");
        result.setData(map);
        return result;
    }





}