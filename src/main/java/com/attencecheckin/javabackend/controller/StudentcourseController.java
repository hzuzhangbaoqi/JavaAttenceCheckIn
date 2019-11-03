
package com.attencecheckin.javabackend.controller;

import com.attencecheckin.javabackend.common.JsonResult;
import com.attencecheckin.javabackend.entity.Studentcourse;
import com.attencecheckin.javabackend.service.StudentcourseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* @Description: StudentcourseController类
* @author 
* @date 2019/11/03 13:43
*/
@RestController
@RequestMapping("/studentcourse")
@Api(value = "studentcourse 在线接口文档")
public class StudentcourseController {

    @Resource
    private StudentcourseService studentcourseService;

    @RequestMapping("/insert")
    @ApiOperation(value = "insert", notes = "增加一条数据")
    public JsonResult<Integer> insert(Studentcourse studentcourse) throws Exception{
      /*studentcourse.setId(ApplicationUtils.getUUID());*/
       studentcourseService.save(studentcourse);
        return new JsonResult<Integer>(1);
    }
    @RequestMapping("/deleteById")
    @ApiOperation(value = "deleteById", notes = "根据id删除数据")
    public JsonResult<Integer> deleteById(@RequestParam Integer id) throws Exception {
        Integer state = studentcourseService.del(id);
        return new JsonResult<Integer>(state);
    }

    @RequestMapping("/update")
    @ApiOperation(value = "update", notes = "更新数据")
    public JsonResult<Integer> update(Studentcourse studentcourse) throws Exception {
        Integer state = studentcourseService.update(studentcourse);
        return new JsonResult<Integer>(state);
    }

    @RequestMapping("/selectById")
    @ApiOperation(value = "selectById", notes = "更加id查询")
    public JsonResult<Studentcourse> selectById(@RequestParam Integer id) throws Exception {
        Studentcourse studentcourse = studentcourseService.get(id);
        return new JsonResult<Studentcourse>(studentcourse);
    }

    /**
     * @Description: 分页查询
     * @param page 页码
     * @param size 每页条数
     * @Reutrn JsonResult<PageInfo<Studentcourse>>
     */
    @RequestMapping("/list")
    @ApiOperation(value = "list", notes = "批量查询")
    public JsonResult<PageInfo<Studentcourse>> list(@RequestParam(defaultValue = "0") Integer page,
               @RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<Studentcourse> list = studentcourseService.getAllList();
        PageInfo<Studentcourse> pageInfo = new PageInfo<Studentcourse>(list);
        return new JsonResult<PageInfo<Studentcourse>>(pageInfo);
    }
}