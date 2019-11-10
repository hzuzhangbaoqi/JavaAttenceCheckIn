
package com.attencecheckin.javabackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.attencecheckin.javabackend.common.enumer.ResultEnum;
import com.attencecheckin.javabackend.service.LoginInfoLogService;
import com.attencecheckin.javabackend.common.JsonResult;
import com.attencecheckin.javabackend.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.attencecheckin.javabackend.entity.LoginInfoLog;
import com.attencecheckin.javabackend.service.LoginInfoLogService;
import io.swagger.annotations.*;
import jdk.nashorn.internal.scripts.JS;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* @Description: LoginInfoLogController类
* @author 
* @date 2019/10/17 09:38
*/
@RestController
@RequestMapping("/logininfolog")
@Api(value = "logininfolog 在线接口文档")
public class LoginInfoLogController {

    @Resource
    private LoginInfoLogService logininfologService;

    @RequestMapping("/insert")
    @ApiOperation(value = "insert", notes = "增加一条数据")
    public JsonResult<Integer> insert(LoginInfoLog logininfolog) throws Exception{
      /*logininfolog.setId(ApplicationUtils.getUUID());*/
       logininfologService.save(logininfolog);
        return new JsonResult<Integer>(1);
    }
    @RequestMapping("/deleteById")
    @ApiOperation(value = "deleteById", notes = "根据id删除数据")
    public JsonResult<Integer> deleteById(@RequestParam @ApiParam(name = "id", value = "主键", required = true)Integer id) throws Exception {
        Integer state = logininfologService.del(id);
        return new JsonResult<Integer>(state);
    }

    @RequestMapping("/update")
    @ApiOperation(value = "update", notes = "更新数据")
    public JsonResult<Integer> update(LoginInfoLog logininfolog) throws Exception {
        Integer state = logininfologService.update(logininfolog);
        return new JsonResult<Integer>(state);
    }

    @RequestMapping("/selectById")
    @ApiOperation(value = "selectById", notes = "更加id查询")
    public JsonResult<LoginInfoLog> selectById(@RequestParam @ApiParam(name = "id", value = "主键", required = true)Integer id) throws Exception {
        LoginInfoLog logininfolog = logininfologService.get(id);
        return new JsonResult<LoginInfoLog>(logininfolog);
    }

    /**
     * @Description: 分页查询
     * @param page 页码
     * @param size 每页条数
     * @Reutrn JsonResult<PageInfo<LoginInfoLog>>
     */
    @RequestMapping("/list")
    @ApiOperation(value = "list", notes = "批量查询")
    public PageInfo<LoginInfoLog> list(@RequestParam(defaultValue = "0")@ApiParam(name = "page", value = "页数", required = true) Integer page,
                                                   @RequestParam(defaultValue = "0")@ApiParam(name = "size", value = "行数", required = true) Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<LoginInfoLog> list = logininfologService.getAllList();
        PageInfo<LoginInfoLog> pageInfo = new PageInfo<LoginInfoLog>(list);
        return pageInfo;
    }

    @RequestMapping("/getStatistical")
    @ApiOperation(value = "selectById", notes = "更加id查询")
    public JSONObject getStatistical() throws Exception {
        Map<String, Object> statistical = logininfologService.getStatistical();
        JsonResult<Map<String, Object>> result = new JsonResult<>(ResultEnum.NORMAL.val(), "查询成功");
        JSONObject json = (JSONObject) JSONObject.toJSON(result);
        json.putAll(statistical);
        return json;
    }
    @RequestMapping("/getLogininfoByWeek")
    @ApiOperation(value = "getLogininfoByWeek", notes = "更加id查询")
    public JsonResult getLogininfoByWeek() throws Exception {
        Map<String, Object> logininfoByWeek = logininfologService.getLogininfoByWeek();
        JsonResult<Map> result = new JsonResult<>(logininfoByWeek);
        result.setData(logininfoByWeek);
        return result;
    }


}