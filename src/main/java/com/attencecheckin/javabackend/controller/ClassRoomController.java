
package com.attencecheckin.javabackend.controller;

import com.attencecheckin.javabackend.common.JsonResult;
import com.attencecheckin.javabackend.entity.ClassRoom;
import com.attencecheckin.javabackend.service.ClassRoomService;
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
* @Description: ClassRoomController类
* @author 
* @date 2019/10/17 09:44
*/
@RestController
@RequestMapping("/classroom")
@Api(value = "classroom 在线接口文档")
public class ClassRoomController {

    @Resource
    private ClassRoomService classroomService;

    @PostMapping("/insert")
    @ApiOperation(value = "insert", notes = "增加一条数据", httpMethod = "POST")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "ClassRoom", value = "实体", required = true, dataType = "ClassRoom")
        })
    public JsonResult<Integer> insert(@RequestBody ClassRoom classroom) throws Exception{
      /*classroom.setId(ApplicationUtils.getUUID());*/
       classroomService.save(classroom);
        return new JsonResult<Integer>(1);
    }
    @PostMapping("/deleteById")
    @ApiOperation(value = "deleteById", notes = "根据id删除数据", httpMethod = "POST")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer")
        })
    public JsonResult<Integer> deleteById(@RequestParam Integer id) throws Exception {
        Integer state = classroomService.del(id);
        return new JsonResult<Integer>(state);
    }

    @PostMapping("/update")
    @ApiOperation(value = "update", notes = "更新数据", httpMethod = "POST")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "ClassRoom", value = "实体", required = true, dataType = "ClassRoom")
        })
    public JsonResult<Integer> update(ClassRoom classroom) throws Exception {
        Integer state = classroomService.update(classroom);
        return new JsonResult<Integer>(state);
    }

    @PostMapping("/selectById")
    @ApiOperation(value = "selectById", notes = "更加id查询", httpMethod = "POST")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer")
        })
    public JsonResult<ClassRoom> selectById(@RequestParam Integer id) throws Exception {
        ClassRoom classroom = classroomService.get(id);
        return new JsonResult<ClassRoom>(classroom);
    }

    /**
     * @Description: 分页查询
     * @param page 页码
     * @param size 每页条数
     * @Reutrn JsonResult<PageInfo<ClassRoom>>
     */
    @PostMapping("/list")
    @ApiOperation(value = "list", notes = "批量查询", httpMethod = "POST")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "page", value = "页数", required = true, dataType = "Integer"),
                @ApiImplicitParam(name = "size", value = "行数", required = true, dataType = "Integer")
        })
    public JsonResult<PageInfo<ClassRoom>> list(@RequestParam(defaultValue = "0") Integer page,
               @RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<ClassRoom> list = classroomService.getAllList();
        PageInfo<ClassRoom> pageInfo = new PageInfo<ClassRoom>(list);
        return new JsonResult<PageInfo<ClassRoom>>(pageInfo);
    }
}