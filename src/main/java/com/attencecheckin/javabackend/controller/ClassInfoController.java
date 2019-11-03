
package com.attencecheckin.javabackend.controller;

import com.attencecheckin.javabackend.service.ClassInfoService;
import com.attencecheckin.javabackend.common.JsonResult;
import com.attencecheckin.javabackend.entity.ClassInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* @Description: ClassInfoController类
* @author 
* @date 2019/10/17 10:30
*/
@RestController
@RequestMapping("/classinfo")
@Api(value = "classinfo 在线接口文档")
public class ClassInfoController {

    @Resource
    private ClassInfoService classinfoService;

    @RequestMapping("/insert")
    @ApiOperation(value = "insert", notes = "增加一条数据")
    public JsonResult<Integer> insert(ClassInfo classinfo) throws Exception{
      /*classinfo.setId(ApplicationUtils.getUUID());*/
       classinfoService.save(classinfo);
        return new JsonResult<Integer>(1);
    }
    @RequestMapping("/deleteById")
    @ApiOperation(value = "deleteById", notes = "根据id删除数据",consumes = "application/x-www-form-urlencoded")
    public JsonResult<Integer> deleteById(@RequestParam @ApiParam(name = "id", value = "id", required = true) Integer id) throws Exception {
        Integer state = classinfoService.del(id);
        return new JsonResult<Integer>(state);
    }


    @RequestMapping("/update")
    @ApiOperation(value = "update", notes = "更新数据",consumes = "application/x-www-form-urlencoded")
    public JsonResult<Integer> update(ClassInfo classinfo) throws Exception {
        Integer state = classinfoService.update(classinfo);
        return new JsonResult<Integer>(state);
    }

    @RequestMapping("/selectById")
    @ApiOperation(value = "selectById", notes = "更加id查询",consumes = "application/x-www-form-urlencoded")
    public JsonResult<ClassInfo> selectById(@RequestParam @ApiParam(name = "id", value = "id", required = true) Integer id) throws Exception {
        ClassInfo classinfo = classinfoService.get(id);
        return new JsonResult<ClassInfo>(classinfo);
    }

    /**
     * @Description: 分页查询
     * @param page 页码
     * @param size 每页条数
     * @Reutrn JsonResult<PageInfo<ClassInfo>>
     */
    @RequestMapping("/list")
    @ApiOperation(value = "list", notes = "批量查询",consumes = "application/x-www-form-urlencoded")
    public PageInfo<ClassInfo> list(@RequestParam(defaultValue = "0")@ApiParam(name = "page", value = "页数", required = true) Integer page,
               @RequestParam(defaultValue = "0")@ApiParam(name = "size", value = "行数", required = true) Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<ClassInfo> list = classinfoService.getAllList();
        PageInfo<ClassInfo> pageInfo = new PageInfo<ClassInfo>(list);
        return pageInfo;
    }
}