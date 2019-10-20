package com.attencecheckin.javabackend.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaMessage;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.binarywang.wx.miniapp.constant.WxMaConstants;
import com.attencecheckin.javabackend.common.JsonResult;
import com.attencecheckin.javabackend.common.enumer.ResultEnum;
import com.attencecheckin.javabackend.config.WxMaConfiguration;
import com.attencecheckin.javabackend.config.WxMaProperties;
import com.attencecheckin.javabackend.entity.Student;
import com.attencecheckin.javabackend.service.StudentService;
import io.swagger.annotations.*;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @program: javabackend
 * @description: 登录
 * @author: zxf
 * @create: 2019-10-16 22:44
 **/
@RestController
@RequestMapping("/login")
@Api(value = "LoginController 登录接口描述文档")
public class LoginController {
    @Value("${wx.miniapp.appid}")
    private String appid;
    @Autowired
    public StudentService studentService;
    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/wechat/studentRegister")
    @ApiOperation(value = "微信登录", notes = "小程序进行登录调用的接口，进行读者账号和微信openid绑定操作", httpMethod = "POST")
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
                //绑定微信
                student.setOpenid(openid);
                Student save = studentService.save(student);
                return new JsonResult(ResultEnum.SUCCESS.val(), "微信绑定成功");
            }
        } catch (WxErrorException e) {
            this.logger.error(e.getMessage(), e);
            return new JsonResult(ResultEnum.SYS_ERROR.val(), "登录异常");
        }
    }



    /**
     * 登陆接口
     */
    @GetMapping("/login")
    public String login(@PathVariable String appid, String code) {
        if (StringUtils.isBlank(code)) {
            return "empty jscode";
        }

        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            this.logger.info(session.getSessionKey());
            this.logger.info(session.getOpenid());
            //TODO 可以增加自己的逻辑，关联业务相关数据
            //return JsonUtils.toJson(session);
            return "";
        } catch (WxErrorException e) {
            this.logger.error(e.getMessage(), e);
            return e.toString();
        }
    }

    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */
    @GetMapping("/info")
    public String info(@PathVariable String appid, String sessionKey,
                       String signature, String rawData, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }

        // 解密用户信息
        WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);

        //return JJsonUtils.toJson(userInfo);
        return"";
    }

    /**
     * <pre>
     * 获取用户绑定手机号信息
     * </pre>
     */
    @GetMapping("/phone")
    public String phone(@PathVariable String appid, String sessionKey, String signature,
                        String rawData, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }

        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);

        //return JsonUtils.toJson(phoneNoInfo);
        return"";
    }

    @GetMapping(produces = "text/plain;charset=utf-8")
    public String authGet(@PathVariable String appid,
                          @RequestParam(name = "signature", required = false) String signature,
                          @RequestParam(name = "timestamp", required = false) String timestamp,
                          @RequestParam(name = "nonce", required = false) String nonce,
                          @RequestParam(name = "echostr", required = false) String echostr) {
        this.logger.info("\n接收到来自微信服务器的认证消息：signature = [{}], timestamp = [{}], nonce = [{}], echostr = [{}]",
                signature, timestamp, nonce, echostr);

        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
            throw new IllegalArgumentException("请求参数非法，请核实!");
        }

        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        if (wxService.checkSignature(timestamp, nonce, signature)) {
            return echostr;
        }

        return "非法请求";
    }

    @PostMapping(produces = "application/xml; charset=UTF-8")
    public String post(@PathVariable String appid,
                       @RequestBody String requestBody,
                       @RequestParam(name = "msg_signature", required = false) String msgSignature,
                       @RequestParam(name = "encrypt_type", required = false) String encryptType,
                       @RequestParam(name = "signature", required = false) String signature,
                       @RequestParam("timestamp") String timestamp,
                       @RequestParam("nonce") String nonce) {
        this.logger.info("\n接收微信请求：[msg_signature=[{}], encrypt_type=[{}], signature=[{}]," +
                        " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
                msgSignature, encryptType, signature, timestamp, nonce, requestBody);

        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        final boolean isJson = Objects.equals(wxService.getWxMaConfig().getMsgDataFormat(),
                WxMaConstants.MsgDataFormat.JSON);
        if (StringUtils.isBlank(encryptType)) {
            // 明文传输的消息
            WxMaMessage inMessage;
            if (isJson) {
                inMessage = WxMaMessage.fromJson(requestBody);
            } else {//xml
                inMessage = WxMaMessage.fromXml(requestBody);
            }

            this.route(inMessage, appid);
            return "success";
        }

        if ("aes".equals(encryptType)) {
            // 是aes加密的消息
            WxMaMessage inMessage;
            if (isJson) {
                inMessage = WxMaMessage.fromEncryptedJson(requestBody, wxService.getWxMaConfig());
            } else {//xml
                inMessage = WxMaMessage.fromEncryptedXml(requestBody, wxService.getWxMaConfig(),
                        timestamp, nonce, msgSignature);
            }

            this.route(inMessage, appid);
            return "success";
        }

        throw new RuntimeException("不可识别的加密类型：" + encryptType);
    }

    private void route(WxMaMessage message, String appid) {
        try {
            WxMaConfiguration.getRouter(appid).route(message);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }
    }


}
