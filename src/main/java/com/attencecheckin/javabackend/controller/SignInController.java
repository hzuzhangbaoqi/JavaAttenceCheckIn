
package com.attencecheckin.javabackend.controller;

import com.attencecheckin.javabackend.common.JsonResult;
import com.attencecheckin.javabackend.common.enumer.ResultEnum;
import com.attencecheckin.javabackend.dao.SignInMapper;
import com.attencecheckin.javabackend.entity.Course;
import com.attencecheckin.javabackend.entity.CourseExample;
import com.attencecheckin.javabackend.entity.SignIn;
import com.attencecheckin.javabackend.entity.SignInExample;
import com.attencecheckin.javabackend.service.CourseService;
import com.attencecheckin.javabackend.service.StudentcourseService;
import com.attencecheckin.javabackend.util.DistanceUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.attencecheckin.javabackend.service.SigninService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Description: SignInController类
* @author 
* @date 2019/10/17 09:30
*/
@RestController
@RequestMapping("/signIn")
@Api(value = "signIn 在线接口文档")
public class SignInController {

    @Resource
    private SigninService signinService;
    @Resource
    private StudentcourseService studentcourseService;

    @Resource
    private CourseService courseService;

    @PostMapping("/insert")
    @ApiOperation(value = "insert", notes = "增加一条数据")
    public JsonResult<Integer> insert(SignIn signIn) throws Exception{
      /*signIn.setId(ApplicationUtils.getUUID());*/
       signinService.save(signIn);
        return new JsonResult<Integer>(1);
    }
    @PostMapping("/deleteById")
    @ApiOperation(value = "deleteById", notes = "根据id删除数据")
    public JsonResult<Integer> deleteById(@RequestParam @ApiParam(name = "id", value = "id", required = true) Integer id) throws Exception {
        Integer state = signinService.del(id);
        return new JsonResult<Integer>(state);
    }

    @PostMapping("/update")
    @ApiOperation(value = "update", notes = "更新数据")
    public JsonResult<Integer> update(SignIn signIn) throws Exception {
        Integer state = signinService.update(signIn);
        return new JsonResult<Integer>(state);
    }

    @PostMapping("/selectById")
    @ApiOperation(value = "selectById", notes = "更加id查询")
    public JsonResult<SignIn> selectById(@RequestParam @ApiParam(name = "id", value = "id", required = true) Integer id) throws Exception {
        SignIn signIn = signinService.get(id);
        return new JsonResult<SignIn>(signIn);
    }

    /**
     * @Description: 分页查询
     * @param page 页码
     * @param size 每页条数
     * @Reutrn JsonResult<PageInfo<SignIn>>
     */
    @PostMapping("/list")
    @ApiOperation(value = "list", notes = "批量查询")
    public JsonResult<PageInfo<SignIn>> list(@RequestParam(defaultValue = "0")@ApiParam(name = "page", value = "页数", required = true) Integer page,
                                             @RequestParam(defaultValue = "0")@ApiParam(name = "size", value = "行数", required = true) Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<SignIn> list = signinService.getAllList();
        PageInfo<SignIn> pageInfo = new PageInfo<SignIn>(list);
        return new JsonResult<PageInfo<SignIn>>(pageInfo);
    }
    @ApiOperation(value = "startSign", notes = "教师点击按钮开始签到")
    @RequestMapping("/startSign")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public JsonResult startSign(HttpServletRequest request, HttpServletResponse response,
                            String teacherId,String time,String location) throws Exception {
        Map<String,Object> map =new HashMap<String,Object>();
        JsonResult result = null;
        //查找课程
        Date startSignTime = DateUtils.parseDate(time, new String[]{"yyyy-MM-dd HH:mm:ss"});

        Course course = courseService.getCourseByTeacheridAndTime(Integer.parseInt(teacherId), time);
        if(course==null){
            result =  new JsonResult(ResultEnum.NOT_DATA.val(), "获取相应课程信息失败");
        }else{
            //查询是否开启了签到
            List<SignIn> signIns = signinService.selectSigninBycourseId(course.getId(), 2);
            if(signIns.size()>0){
                result =  new JsonResult(ResultEnum.FAIL.val(), "已经开启签到成功，请勿重复开启");
            }else{
                try {
                    //记录开始签到记录
                    SignIn signIn = new SignIn();
                    signIn.setStudentid(Integer.parseInt(teacherId));
                    signIn.setSigntime(startSignTime);
                    signIn.setStatus(1);
                    signIn.setCourseid(course.getId());
                    signIn.setSuslocationstatus(0);
                    signIn.setSuslocation(location);
                    signIn.setSigntype(2);
                    SignIn save = signinService.save(signIn);
                    //记录学生的未签记录
                    List<Integer> studentids = studentcourseService.getStudentidsByCourse(course.getId());
                    for (Integer studentid : studentids) {
                        SignIn studentSignIn = new SignIn();
                        studentSignIn.setStudentid(studentid);
                        studentSignIn.setStatus(0);
                        studentSignIn.setCourseid(course.getId());
                        signinService.save(studentSignIn);
                    }
                    result = new JsonResult(ResultEnum.NORMAL.val(), "开始签到成功");
                    map.put("courseId", course.getId());
                    result.setData(map);
                }catch (Exception e){
                    result = new JsonResult(ResultEnum.EXCEPTION.val(), e.getMessage());
                }
            }
        }
        return result;
    }
    @ApiOperation(value = "startSign", notes = "查看课程的签到情况")
    @RequestMapping("/showStudentSign")
    public JsonResult startSign(HttpServletRequest request, HttpServletResponse response,
                                Integer courseid,String time) throws Exception {
        JsonResult result = null;
        Date signTime = DateUtils.parseDate(time, new String[]{"yyyy-MM-dd HH:mm:ss"});
        String format = DateFormatUtils.format(signTime, "yyyy-MM-dd");
        List<SignIn> list = signinService.showSignin(courseid, format, 1);
        result = new JsonResult(ResultEnum.NORMAL.val(), "查看学生签到情况");
        result.setData(list);
        return  result;
    }

    @ApiOperation(value = "isCanSign", notes = "查看课程是否可以开始签到")
    @RequestMapping("/isCanSign")
    public JsonResult isCanSign(HttpServletRequest request, HttpServletResponse response,
                            String studentId,Integer courseid) throws Exception {
        JsonResult result = null;
        //查询是否开启了签到
        List<SignIn> signIns = signinService.selectSigninBycourseId(courseid, 2);
        if(signIns.size()>0){
            result =  new JsonResult(ResultEnum.NORMAL.val(), "学生可以进行签到");
        }else{
            result =  new JsonResult(ResultEnum.FAIL.val(), "教师还未开启签到");
        }
        return result;
    }

    @RequestMapping("/studentSign")
    public JsonResult studentSign(HttpServletRequest request, HttpServletResponse response,
                                Integer studentId,String location,Integer courseid) throws Exception {
        List<SignIn> signIns = signinService.selectSigninBycourseId(courseid, 2);
        //获取教师位置
        SignIn signIn = signIns.get(0);
        double distance = DistanceUtils.getDistance(signIn.getSuslocation(), location);
        int studentSignin = signinService.studentSignin(studentId, courseid, location, distance > 300 ? 1 : 0);
        if(studentSignin>0){
            return new JsonResult(ResultEnum.NORMAL.val(), "签到成功");
        }
        return new JsonResult(ResultEnum.FAIL.val(), "签到失败");
    }
}