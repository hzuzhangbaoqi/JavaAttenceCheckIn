
package com.attencecheckin.javabackend.controller;

import com.attencecheckin.javabackend.service.TeacherService;
import com.attencecheckin.javabackend.common.JsonResult;
import com.attencecheckin.javabackend.entity.Teacher;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.attencecheckin.javabackend.service.TeacherService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* @Description: TeacherController类
* @author 
* @date 2019/10/17 02:47
*/
@RestController
@RequestMapping("/teacher")
@Api(value = "Teacher 在线接口文档")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    @PostMapping("/insert")
    @ApiOperation(value = "insert", notes = "增加一条数据", httpMethod = "POST")
    public JsonResult<Integer> insert( Teacher teacher) throws Exception{

        teacherService.save(teacher);
        return new JsonResult<Integer>(1);
    }
    @PostMapping("/deleteById")
    @ApiOperation(value = "deleteById", notes = "根据id删除数据", httpMethod = "POST")
    public JsonResult<Integer> deleteById(@RequestParam @ApiParam(name = "id", value = "主键", required = true)Integer id) throws Exception {
        Integer state = teacherService.del(id);
        return new JsonResult<Integer>(state);
    }

    @PostMapping("/update")
    @ApiOperation(value = "update", notes = "更新数据", httpMethod = "POST")
    public JsonResult<Integer> update(Teacher teacher) throws Exception {
        Integer state = teacherService.update(teacher);
        return new JsonResult<Integer>(state);
    }

    @PostMapping("/selectById")
    @ApiOperation(value = "selectById", notes = "更加id查询", httpMethod = "POST")
    public JsonResult<Teacher> selectById(@RequestParam@ApiParam(name = "id", value = "主键", required = true) Integer id) throws Exception {
        Teacher teacher = teacherService.get(id);
        return new JsonResult<Teacher>(teacher);
    }

    /**
     * @Description: 分页查询
     * @param page 页码
     * @param size 每页条数
     * @Reutrn JsonResult<PageInfo<Teacher>>
     */
    @PostMapping("/list")
    @ApiOperation(value = "list", notes = "批量查询", httpMethod = "POST")
    public JsonResult<PageInfo<Teacher>> list(@RequestParam(defaultValue = "0")@ApiParam(name = "page", value = "页数", required = true) Integer page,
                                              @RequestParam(defaultValue = "0")@ApiParam(name = "size", value = "行数", required = true) Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<Teacher> list = teacherService.getAllList();
        PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(list);
        return new JsonResult<PageInfo<Teacher>>(pageInfo);
    }
}