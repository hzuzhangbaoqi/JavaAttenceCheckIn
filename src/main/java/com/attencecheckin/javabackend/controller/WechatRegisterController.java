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
import io.swagger.annotations.*;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @program: javabackend
 * @description: 微信注册
 * @author: zxf
 * @create: 2019-10-19 17:20
 **/
@RestController
@RequestMapping("/wechat/register")
@Api(value = "WechatRegister 微信注册登录接口描述文档")
public class WechatRegisterController {

    @Value("${wx.miniapp.configs.appid}")
    private String appid;
    @Autowired
    public StudentService studentService;
    @Autowired
    public TeacherService teacherService;
    private final static Logger logger = LoggerFactory.getLogger(WechatRegisterController.class);

    @PostMapping(value="/wechat/student")
    @ApiOperation(value = "学生注册", notes = "小程序进行注册登录调用的接口，进行读者账号和微信openid绑定操作")
    public JsonResult<Student> wechatStudentLogin(HttpServletRequest request, HttpServletResponse response,@RequestBody Student student) {
        if (studentService.existById(student.getId())) return new JsonResult(ResultEnum.NOT_DATA.val(), "学生账号存在");
        if (StringUtils.isBlank(student.getPassword())) {
            return new JsonResult(ResultEnum.PARAMS_ERROR.val(), "密码不能为空");
        }
        try {
            Student save = studentService.save(student);
            return new JsonResult(ResultEnum.NORMAL.val(), "注册成功");
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
            return new JsonResult(ResultEnum.SYS_ERROR.val(), "注册异常");
        }
    }
    @PostMapping("/wechat/teacher")
    @ApiOperation(value = "教师注册", notes = "小程序进行注册登录调用的接口，进行读者账号和微信openid绑定操作")
    public JsonResult<Student> wechatStudentLogin(HttpServletRequest request, HttpServletResponse response,@RequestBody Teacher teacher) {

        if (teacherService.existById(teacher.getId())) return new JsonResult(ResultEnum.NOT_DATA.val(), "教师账号存在");
        if (StringUtils.isBlank(teacher.getPassword())) {
            return new JsonResult(ResultEnum.PARAMS_ERROR.val(), "密码不能为空");
        }
        try {
            Teacher save = teacherService.save(teacher);
            return new JsonResult(ResultEnum.NORMAL.val(), "注册成功");
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
            return new JsonResult(ResultEnum.SYS_ERROR.val(), "注册异常");
        }
    }
}
