package com.attencecheckin.javabackend.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.attencecheckin.javabackend.common.JsonResult;
import com.attencecheckin.javabackend.common.enumer.ResultEnum;
import com.attencecheckin.javabackend.config.WxMaConfiguration;
import com.attencecheckin.javabackend.entity.Student;
import com.attencecheckin.javabackend.entity.Teacher;
import com.attencecheckin.javabackend.service.StudentService;
import com.attencecheckin.javabackend.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: javabackend
 * @description:
 * @author: zxf
 * @create: 2019-10-19 17:34
 **/
@RestController
@RequestMapping("/wechat/login")
@Api(value = "WechatLoginController 登录接口描述文档")
public class WechatLoginController {
    @Value("${wx.miniapp.appid}")
    private String appid;
    @Autowired
    public StudentService studentService;
    @Autowired
    public TeacherService teacherService;



    private final static Logger logger = LoggerFactory.getLogger(WechatLoginController.class);

    @PostMapping("/login")
    @ApiOperation(value = "微信登录", notes = "小程序进行登录调用的接口，进行读者账号和微信openid绑定操作", httpMethod = "POST")
    public JsonResult wechatLogin(@RequestParam(name = "id", required = false) Integer id,
                                           @RequestParam(name = "passWord", required = false) String passWord,
                                           @RequestParam(name = "jscode", required = true) String jscode,
                                           @RequestParam(name = "usertype", required = true) String usertype,
                                           HttpServletRequest request, HttpServletResponse response) {
        try {
            if(usertype.equalsIgnoreCase("student")){
                request.getRequestDispatcher("/wechat/login/studentLogin").forward(request, response);
            }else if(usertype.equalsIgnoreCase("teacher")){
                request.getRequestDispatcher("/wechat/login/teacherLogin").forward(request, response);
            }else{
                return new JsonResult(ResultEnum.PARAMS_ERROR.val(), "请填写用户类型：teacher或student");
            }
        } catch (ServletException e) {
            return new JsonResult(ResultEnum.SYS_ERROR.val(), "Servlet登录异常");
        } catch (IOException e) {
            return new JsonResult(ResultEnum.SYS_ERROR.val(), "IOException登录异常");
        }
        return new JsonResult(ResultEnum.EXCEPTION_NullPointer.val(), ResultEnum.EXCEPTION_NullPointer.msg());
    }



    @PostMapping("/studentLogin")
    @ApiOperation(value = "学生微信登录", notes = "学生微信登录,小程序进行登录调用的接口，进行读者账号和微信openid绑定操作", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "passWord", value = "用户密码", required = false, dataType = "String"),
            @ApiImplicitParam(name = "jscode", value = "jscode", required = true, dataType = "String")
    })
    public JsonResult<Student> wechatStudentLogin(@RequestParam(value = "id", required = false) Integer id,
                                                  @RequestParam(value = "passWord", required = false) String passWord,
                                                  @RequestParam(value = "jscode", required = false) String jscode) {

        final WxMaService wxService = WxMaConfiguration.getMaService(appid);
        Student student = studentService.get(id);
        if (student == null) return new JsonResult(ResultEnum.NOT_DATA.val(), "学生账号不存在");
        if (!student.getPassword().equals(passWord)) {
            return new JsonResult(ResultEnum.PARAMS_ERROR.val(), "密码错误");
        }
        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(jscode);
            this.logger.info(session.getSessionKey());
            this.logger.info(session.getOpenid());
            //TODO 可以增加自己的逻辑，关联业务相关数据
            String openid = session.getOpenid();
            boolean flg = false;
            if (StringUtils.isNotBlank(student.getOpenid())) {
                if (student.getOpenid().equals(openid)) {
                    return new JsonResult(ResultEnum.SUCCESS.val(), "登录成功");
                } else {
                    return new JsonResult(ResultEnum.FAIL.val(), "只能使用已绑微信进行登录");
                }
            } else {
                //初次登录，绑定微信
                student.setOpenid(openid);
                Student save = studentService.save(student);
                return new JsonResult(ResultEnum.SUCCESS.val(), "微信绑定成功，登录成功");
            }
        } catch (WxErrorException e) {
            this.logger.error(e.getMessage(), e);
            return new JsonResult(ResultEnum.SYS_ERROR.val(), "登录异常"+e.getMessage());
        }
    }


    @PostMapping("/teacherLogin")
    @ApiOperation(value = "教师微信登录", notes = "教师微信登录小程序进行登录调用的接口，进行读者账号和微信openid绑定操作", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "passWord", value = "用户密码", required = false, dataType = "String"),
            @ApiImplicitParam(name = "jscode", value = "jscode", required = true, dataType = "String")
    })
    public JsonResult<Student> wechatTeacherLogin(@RequestParam(value = "id", required = false) Integer id,
                                                  @RequestParam(value = "passWord", required = false) String passWord,
                                                  @RequestParam(value = "jscode", required = false) String jscode) {

        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        Teacher teacher = teacherService.get(id);
        if (teacher == null) return new JsonResult(ResultEnum.NOT_DATA.val(), "教师账号不存在");
        if (!teacher.getPassword().equals(passWord)) {
            return new JsonResult(ResultEnum.PARAMS_ERROR.val(), "密码错误");
        }
        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(jscode);
            this.logger.info(session.getSessionKey());
            this.logger.info(session.getOpenid());
            //TODO 可以增加自己的逻辑，关联业务相关数据
            String openid = session.getOpenid();
            boolean flg = false;
            if (StringUtils.isNotBlank(teacher.getOpenid())) {
                if (teacher.getOpenid().equals(openid)) {
                    return new JsonResult(ResultEnum.SUCCESS.val(), "登录成功");
                } else {
                    return new JsonResult(ResultEnum.FAIL.val(), "只能使用已绑微信进行登录");
                }
            } else {
                //初次登录，绑定微信
                teacher.setOpenid(openid);
                Teacher save = teacherService.save(teacher);
                return new JsonResult(ResultEnum.SUCCESS.val(), "微信绑定成功，登录成功");
            }
        } catch (WxErrorException e) {
            this.logger.error(e.getMessage(), e);
            return new JsonResult(ResultEnum.SYS_ERROR.val(), "登录异常"+e.getMessage());
        }
    }

}
