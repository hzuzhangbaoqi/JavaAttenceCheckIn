
package com.attencecheckin.javabackend.controller;

import com.attencecheckin.javabackend.service.LoginInfoLogService;
import com.attencecheckin.javabackend.common.JsonResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.attencecheckin.javabackend.entity.LoginInfoLog;
import com.attencecheckin.javabackend.service.LoginInfoLogService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @PostMapping("/insert")
    @ApiOperation(value = "insert", notes = "增加一条数据", httpMethod = "POST")
    public JsonResult<Integer> insert(@RequestBody LoginInfoLog logininfolog) throws Exception{
      /*logininfolog.setId(ApplicationUtils.getUUID());*/
       logininfologService.save(logininfolog);
        return new JsonResult<Integer>(1);
    }
    @PostMapping("/deleteById")
    @ApiOperation(value = "deleteById", notes = "根据id删除数据", httpMethod = "POST")
    public JsonResult<Integer> deleteById(@RequestParam @ApiParam(name = "id", value = "主键", required = true)Integer id) throws Exception {
        Integer state = logininfologService.del(id);
        return new JsonResult<Integer>(state);
    }

    @PostMapping("/update")
    @ApiOperation(value = "update", notes = "更新数据", httpMethod = "POST")
    public JsonResult<Integer> update(@RequestBody LoginInfoLog logininfolog) throws Exception {
        Integer state = logininfologService.update(logininfolog);
        return new JsonResult<Integer>(state);
    }

    @PostMapping("/selectById")
    @ApiOperation(value = "selectById", notes = "更加id查询", httpMethod = "POST")
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
    @PostMapping("/list")
    @ApiOperation(value = "list", notes = "批量查询", httpMethod = "POST")
    public JsonResult<PageInfo<LoginInfoLog>> list(@RequestParam(defaultValue = "0")@ApiParam(name = "page", value = "页数", required = true) Integer page,
                                                   @RequestParam(defaultValue = "0")@ApiParam(name = "size", value = "行数", required = true) Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<LoginInfoLog> list = logininfologService.getAllList();
        PageInfo<LoginInfoLog> pageInfo = new PageInfo<LoginInfoLog>(list);
        return new JsonResult<PageInfo<LoginInfoLog>>(pageInfo);
    }
}