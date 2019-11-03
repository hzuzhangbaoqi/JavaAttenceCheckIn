
package com.attencecheckin.javabackend.controller;

import com.attencecheckin.javabackend.service.StudentService;
import com.attencecheckin.javabackend.common.JsonResult;
import com.attencecheckin.javabackend.entity.Student;
import com.attencecheckin.javabackend.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* @Description: StudentController类
* @author 
* @date 2019/10/17 02:38
*/
@RestController
@RequestMapping("/student")
@Api(value = "Student 在线接口文档")
public class StudentController {

    @Resource
    private StudentService studentService;

    @PostMapping("/insert")
    @ApiOperation(value = "insert", notes = "增加一条数据")
    public JsonResult<Integer> insert(Student student) throws Exception{

        studentService.save(student);
        return new JsonResult<Integer>(1);
    }
    @PostMapping("/deleteById")
    @ApiOperation(value = "deleteById", notes = "根据id删除数据")
    public JsonResult<Integer> deleteById(@RequestParam @ApiParam(name = "id", value = "主键", required = true) Integer id) throws Exception {
        Integer state = studentService.del(id);
        return new JsonResult<Integer>(state);
    }

    @PostMapping("/update")
    @ApiOperation(value = "update", notes = "更新数据")
    public JsonResult<Integer> update(Student student) throws Exception {
        Integer state = studentService.update(student);
        return new JsonResult<Integer>(state);
    }

    @PostMapping("/selectById")
    @ApiOperation(value = "selectById", notes = "更加id查询")
    public JsonResult<Student> selectById(@RequestParam  @ApiParam(name = "id", value = "主键", required = true)Integer id) throws Exception {
        Student student = studentService.get(id);
        return new JsonResult<Student>(student);
    }

    /**
     * @Description: 分页查询
     * @param page 页码
     * @param size 每页条数
     * @Reutrn JsonResult<PageInfo<Student>>
     */
    @PostMapping("/list")
    @ApiOperation(value = "list", notes = "批量查询")
    public JsonResult<PageInfo<Student>> list(@RequestParam(defaultValue = "0")@ApiParam(name = "page", value = "页数", required = true) Integer page,
                                              @RequestParam(defaultValue = "0")@ApiParam(name = "size", value = "行数", required = true) Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<Student> list = studentService.getAllList();
        PageInfo<Student> pageInfo = new PageInfo<Student>(list);
        return new JsonResult<PageInfo<Student>>(pageInfo);
    }
}