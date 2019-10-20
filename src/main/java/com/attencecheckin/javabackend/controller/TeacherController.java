
package com.attencecheckin.javabackend.controller;

import com.attencecheckin.javabackend.service.TeacherService;
import com.attencecheckin.javabackend.common.JsonResult;
import com.attencecheckin.javabackend.entity.Teacher;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.attencecheckin.javabackend.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
    @ApiImplicitParams({
                @ApiImplicitParam(name = "Teacher", value = "实体", required = true, dataType = "Teacher")
        })
    public JsonResult<Integer> insert(@RequestBody Teacher teacher) throws Exception{

        teacherService.save(teacher);
        return new JsonResult<Integer>(1);
    }
    @PostMapping("/deleteById")
    @ApiOperation(value = "deleteById", notes = "根据id删除数据", httpMethod = "POST")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer")
        })
    public JsonResult<Integer> deleteById(@RequestParam Integer id) throws Exception {
        Integer state = teacherService.del(id);
        return new JsonResult<Integer>(state);
    }

    @PostMapping("/update")
    @ApiOperation(value = "update", notes = "更新数据", httpMethod = "POST")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "Teacher", value = "实体", required = true, dataType = "Teacher")
        })
    public JsonResult<Integer> update(Teacher teacher) throws Exception {
        Integer state = teacherService.update(teacher);
        return new JsonResult<Integer>(state);
    }

    @PostMapping("/selectById")
    @ApiOperation(value = "selectById", notes = "更加id查询", httpMethod = "POST")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer")
        })
    public JsonResult<Teacher> selectById(@RequestParam Integer id) throws Exception {
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
    @ApiImplicitParams({
                @ApiImplicitParam(name = "page", value = "页数", required = true, dataType = "Integer"),
                @ApiImplicitParam(name = "size", value = "行数", required = true, dataType = "Integer")
        })
    public JsonResult<PageInfo<Teacher>> list(@RequestParam(defaultValue = "0") Integer page,
               @RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<Teacher> list = teacherService.getAllList();
        PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(list);
        return new JsonResult<PageInfo<Teacher>>(pageInfo);
    }
}