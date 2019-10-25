
package com.attencecheckin.javabackend.controller;

import com.attencecheckin.javabackend.common.JsonResult;
import com.attencecheckin.javabackend.entity.SignIn;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.attencecheckin.javabackend.service.SigninService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @PostMapping("/insert")
    @ApiOperation(value = "insert", notes = "增加一条数据", httpMethod = "POST")
    public JsonResult<Integer> insert(@RequestBody SignIn signIn) throws Exception{
      /*signIn.setId(ApplicationUtils.getUUID());*/
       signinService.save(signIn);
        return new JsonResult<Integer>(1);
    }
    @PostMapping("/deleteById")
    @ApiOperation(value = "deleteById", notes = "根据id删除数据", httpMethod = "POST")
    public JsonResult<Integer> deleteById(@RequestParam @ApiParam(name = "id", value = "id", required = true) Integer id) throws Exception {
        Integer state = signinService.del(id);
        return new JsonResult<Integer>(state);
    }

    @PostMapping("/update")
    @ApiOperation(value = "update", notes = "更新数据", httpMethod = "POST")
    public JsonResult<Integer> update(@RequestBody SignIn signIn) throws Exception {
        Integer state = signinService.update(signIn);
        return new JsonResult<Integer>(state);
    }

    @PostMapping("/selectById")
    @ApiOperation(value = "selectById", notes = "更加id查询", httpMethod = "POST")
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
    @ApiOperation(value = "list", notes = "批量查询", httpMethod = "POST")
    public JsonResult<PageInfo<SignIn>> list(@RequestParam(defaultValue = "0")@ApiParam(name = "page", value = "页数", required = true) Integer page,
                                             @RequestParam(defaultValue = "0")@ApiParam(name = "size", value = "行数", required = true) Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<SignIn> list = signinService.getAllList();
        PageInfo<SignIn> pageInfo = new PageInfo<SignIn>(list);
        return new JsonResult<PageInfo<SignIn>>(pageInfo);
    }
}