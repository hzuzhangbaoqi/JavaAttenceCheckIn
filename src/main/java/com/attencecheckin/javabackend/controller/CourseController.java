
package com.attencecheckin.javabackend.controller;

import com.attencecheckin.javabackend.common.JsonResult;
import com.attencecheckin.javabackend.entity.Course;
import com.attencecheckin.javabackend.service.CourseService;
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
* @Description: CourseController类
* @author 
* @date 2019/10/17 09:42
*/
@RestController
@RequestMapping("/course")
@Api(value = "course 在线接口文档")
public class CourseController {

    @Resource
    private CourseService courseService;

    @PostMapping("/insert")
    @ApiOperation(value = "insert", notes = "增加一条数据", httpMethod = "POST")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "Course", value = "实体", required = true, dataType = "Course")
        })
    public JsonResult<Integer> insert(@RequestBody Course course) throws Exception{
      /*course.setId(ApplicationUtils.getUUID());*/
       courseService.save(course);
        return new JsonResult<Integer>(1);
    }
    @PostMapping("/deleteById")
    @ApiOperation(value = "deleteById", notes = "根据id删除数据", httpMethod = "POST")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer")
        })
    public JsonResult<Integer> deleteById(@RequestParam Integer id) throws Exception {
        Integer state = courseService.del(id);
        return new JsonResult<Integer>(state);
    }

    @PostMapping("/update")
    @ApiOperation(value = "update", notes = "更新数据", httpMethod = "POST")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "Course", value = "实体", required = true, dataType = "Course")
        })
    public JsonResult<Integer> update(Course course) throws Exception {
        Integer state = courseService.update(course);
        return new JsonResult<Integer>(state);
    }

    @PostMapping("/selectById")
    @ApiOperation(value = "selectById", notes = "更加id查询", httpMethod = "POST")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer")
        })
    public JsonResult<Course> selectById(@RequestParam Integer id) throws Exception {
        Course course = courseService.get(id);
        return new JsonResult<Course>(course);
    }

    /**
     * @Description: 分页查询
     * @param page 页码
     * @param size 每页条数
     * @Reutrn JsonResult<PageInfo<Course>>
     */
    @PostMapping("/list")
    @ApiOperation(value = "list", notes = "批量查询", httpMethod = "POST")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "page", value = "页数", required = true, dataType = "Integer"),
                @ApiImplicitParam(name = "size", value = "行数", required = true, dataType = "Integer")
        })
    public JsonResult<PageInfo<Course>> list(@RequestParam(defaultValue = "0") Integer page,
               @RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<Course> list = courseService.getAllList();
        PageInfo<Course> pageInfo = new PageInfo<Course>(list);
        return new JsonResult<PageInfo<Course>>(pageInfo);
    }
}