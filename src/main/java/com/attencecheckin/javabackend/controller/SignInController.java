
package com.attencecheckin.javabackend.controller;

import com.attencecheckin.javabackend.common.JsonResult;
import com.attencecheckin.javabackend.common.enumer.ResultEnum;
import com.attencecheckin.javabackend.entity.Course;
import com.attencecheckin.javabackend.entity.SignIn;
import com.attencecheckin.javabackend.entity.Student;
import com.attencecheckin.javabackend.service.CourseService;
import com.attencecheckin.javabackend.service.StudentService;
import com.attencecheckin.javabackend.util.DistanceUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.attencecheckin.javabackend.service.SigninService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

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
    private CourseService courseService;

    @Resource
    private StudentService studentService;

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
        Date startSignTime = DateUtils.parseDate(time, new String[]{"yyyy-MM-dd hh:mm:ss"});

        Course course = courseService.getCourseByTeacheridAndTime(Integer.parseInt(teacherId), time);
        if(course==null){
            result =  new JsonResult(ResultEnum.NOT_DATA.val(), "教师当前时段没有对应课程，开始签到失败");
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
                    List<String> courseids = Arrays.asList((course.getId() + "").split(","));
                    List<Integer> studentids = studentService.getStudentidsByCourse(courseids);
                    for (Integer studentid : studentids) {
                        SignIn studentSignIn = new SignIn();
                        studentSignIn.setSigntime(new Date());
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
        if(StringUtils.isBlank(time)){
            //Date signTime = DateUtils.parseDate(time, new String[]{"yyyy-MM-dd hh:mm:ss"});
            time = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        }
        List<SignIn> list = signinService.showSignin(courseid, time, 1);
        result = new JsonResult(ResultEnum.NORMAL.val(), "查看学生签到情况");
        result.setData(list);
        return  result;
    }

    @ApiOperation(value = "isCanSign", notes = "查看课程是否可以开始签到")
    @RequestMapping("/isCanSign")
    public JsonResult isCanSign(HttpServletRequest request, HttpServletResponse response,
                            String studentId) throws Exception {
        JsonResult result = null;
        Date date = new Date();
        String e = DateFormatUtils.format(date, "E");
        if(e.equals("星期一")){ e="1";}
        else if(e.equals("星期二")){ e="2";}
        else if(e.equals("星期三")){ e="3";}
        else if(e.equals("星期四")){ e="4";}
        else if(e.equals("星期五")){ e="5";}
        else if(e.equals("星期六")){ e="6";}
        else if(e.equals("星期日")){ e="0";}
        //判断当前时间段
        String format = DateFormatUtils.format(date, "yyyy-MM-dd ");
        Integer jieci = null;
        if(belongCalendar(date, DateUtils.parseDate(format+"08:00:00", new String[]{"yyyy-MM-dd hh:mm:ss"})
                , DateUtils.parseDate(format+"09:45:00", new String[]{"yyyy-MM-dd hh:mm:ss"}))){
            jieci = 1;
        }else if(belongCalendar(date, DateUtils.parseDate(format+"10:00:00", new String[]{"yyyy-MM-dd hh:mm:ss"})
                , DateUtils.parseDate(format+"11:45:00", new String[]{"yyyy-MM-dd hh:mm:ss"}))){
            jieci = 2;
        }else if(belongCalendar(date, DateUtils.parseDate(format+"14:00:00", new String[]{"yyyy-MM-dd hh:mm:ss"})
                , DateUtils.parseDate(format+"15:45:00", new String[]{"yyyy-MM-dd hh:mm:ss"}))){
            jieci = 3;
        }else if(belongCalendar(date, DateUtils.parseDate(format+"16:00:00", new String[]{"yyyy-MM-dd hh:mm:ss"})
                , DateUtils.parseDate(format+"17:45:00", new String[]{"yyyy-MM-dd hh:mm:ss"}))){
            jieci = 4;
        }else if(belongCalendar(date, DateUtils.parseDate(format+"18:00:00", new String[]{"yyyy-MM-dd hh:mm:ss"})
                , DateUtils.parseDate(format+"23:45:00", new String[]{"yyyy-MM-dd hh:mm:ss"}))){
            jieci = 5;
        }else{
            result =  new JsonResult(ResultEnum.NOT_LOGIN.val(), "当前时段不再上课时间段内");
            return result;

        }
        //有week和节次，查询courseid
        Student student = studentService.get(Integer.parseInt(studentId));
        if(student==null){
            result =  new JsonResult(ResultEnum.NOT_LOGIN.val(), "学生信息不存在");
            return result;
        }
        if(student.getClassid()==null){
            result =  new JsonResult(ResultEnum.PARAMS_ERROR.val(), "学生班级不存在");
            return result;
        }
        Course course = courseService.getCourseByWeekAndJieci(Integer.parseInt(e), jieci, student.getClassid());

        //查询是否开启了签到
        List<SignIn> signIns = signinService.selectSigninBycourseId(course.getId(), 2);
        if(signIns.size()>0){
            result =  new JsonResult(ResultEnum.NORMAL.val(), "学生可以进行签到");
        }else{
            result =  new JsonResult(ResultEnum.FAIL.val(), "教师还未开启签到");
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("courseid", course.getId());
        result.setData(map);
        return result;
    }
    /**
     *判断时间是否在时间段内
     *@paramnowTime
     *@parambeginTime
     *@paramendTime
     *@return
     */
    public static boolean belongCalendar(Date nowTime,Date beginTime,Date endTime){
    //设置当前时间
        Calendar date=Calendar.getInstance();
        date.setTime(nowTime);
    //设置开始时间
        Calendar begin=Calendar.getInstance();
        begin.setTime(beginTime);
        //设置结束时间
        Calendar end=Calendar.getInstance();
        end.setTime(endTime);
        //处于开始时间之后，和结束时间之前的判断
        if(date.after(begin)&&date.before(end)){
            return true;
        }else{
            return false;
        }
    }

    @RequestMapping("/studentSign")
    public JsonResult studentSign(HttpServletRequest request, HttpServletResponse response,
                                Integer studentId,String location,Integer courseid) throws Exception {
        List<SignIn> signIns = signinService.selectSigninBycourseId(courseid, 2);
        //获取教师位置
        if(signIns.size()<=0){
            return new JsonResult(ResultEnum.NOT_DATA.val(), "单签没有需要签到的课程");
        }
        SignIn signIn = signIns.get(0);
        double distance = DistanceUtils.getDistance(signIn.getSuslocation(), location);
        int studentSignin = signinService.studentSignin(studentId, courseid, location, distance > 300 ? 1 : 0);
        if(studentSignin>0){
            return new JsonResult(ResultEnum.NORMAL.val(), "签到成功");
        }
        return new JsonResult(ResultEnum.FAIL.val(), "签到失败");
    }


    @RequestMapping("/getAbnormal")
    @ApiOperation(value = "getAbnormal", notes = "获取异常的签到")
    public List<SignIn> getAbnormal(@RequestParam @ApiParam(name = "status", value = "状态，多个用,分割", required = true) String status,
                                          @RequestParam @ApiParam(name = "users", value = "用户users", required = true) String users) throws Exception {
        List<Integer> userList = Arrays.asList(users.split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
        List<Integer> statuList = Arrays.asList(status.split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
        List<SignIn> abnormal = signinService.getAbnormal(userList, statuList);
        return abnormal;
    }
    @RequestMapping("/getAttendanceStatistics")
    @ApiOperation(value = "getAttendanceStatistics", notes = "获取异常的签到")
    public List<Map<String, Object>> getAttendanceStatistics(String signtimeStart ,String signtimeEnd,Integer studentid) throws Exception {
        List<Map<String, Object>> attendanceStatistics = signinService.getAttendanceStatistics(signtimeStart, signtimeEnd, studentid);
        return attendanceStatistics;
    }

}