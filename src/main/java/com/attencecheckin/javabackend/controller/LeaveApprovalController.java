
package com.attencecheckin.javabackend.controller;

import com.attencecheckin.javabackend.common.enumer.ResultEnum;
import com.attencecheckin.javabackend.service.LeaveApprovalService;
import com.attencecheckin.javabackend.common.JsonResult;
import com.attencecheckin.javabackend.entity.LeaveApproval;
import com.attencecheckin.javabackend.service.LeaveApprovalService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* @Description: LeaveApprovalController类
* @author 
* @date 2019/10/17 09:41
*/
@RestController
@RequestMapping("/leaveapproval")
@Api(value = "leaveapproval 在线接口文档")
public class LeaveApprovalController {

    @Resource
    private LeaveApprovalService leaveapprovalService;

    @RequestMapping("/insert")
    @ApiOperation(value = "insert", notes = "增加一条数据")
    public JsonResult<Integer> insert(LeaveApproval leaveapproval) throws Exception{
      /*leaveapproval.setId(ApplicationUtils.getUUID());*/
       leaveapprovalService.save(leaveapproval);
        return new JsonResult<Integer>(1);
    }
    @RequestMapping("/deleteById")
    @ApiOperation(value = "deleteById", notes = "根据id删除数据")
    public JsonResult<Integer> deleteById(@RequestParam  @ApiParam(name = "id", value = "id", required = true)Integer id) throws Exception {
        Integer state = leaveapprovalService.del(id);
        return new JsonResult<Integer>(state);
    }

    @RequestMapping("/update")
    @ApiOperation(value = "update", notes = "更新数据")
    public JsonResult<Integer> update(LeaveApproval leaveapproval) throws Exception {
        Integer state = leaveapprovalService.update(leaveapproval);
        return new JsonResult<Integer>(state);
    }

    @RequestMapping("/selectById")
    @ApiOperation(value = "selectById", notes = "更加id查询")
    public JsonResult<LeaveApproval> selectById(@RequestParam  @ApiParam(name = "id", value = "id", required = true)Integer id) throws Exception {
        LeaveApproval leaveapproval = leaveapprovalService.get(id);
        return new JsonResult<LeaveApproval>(leaveapproval);
    }

    /**
     * @Description: 分页查询
     * @param page 页码
     * @param size 每页条数
     * @Reutrn JsonResult<PageInfo<LeaveApproval>>
     */
    @RequestMapping("/list")
    @ApiOperation(value = "list", notes = "批量查询")
    public JsonResult<PageInfo<LeaveApproval>> list(@RequestParam(defaultValue = "0")@ApiParam(name = "page", value = "页数", required = true) Integer page,
                                                    @RequestParam(defaultValue = "0")@ApiParam(name = "size", value = "行数", required = true) Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<LeaveApproval> list = leaveapprovalService.getAllList();
        PageInfo<LeaveApproval> pageInfo = new PageInfo<LeaveApproval>(list);
        return new JsonResult<PageInfo<LeaveApproval>>(pageInfo);
    }

    @RequestMapping("/leaveCourse")
    @ApiOperation(value = "leaveCourse", notes = "请假申请")
    public JsonResult<LeaveApproval> leaveCourse(String studentId,
                                           String leaveReason,
                                           String startleavedate,
                                                 String endleavedate) throws Exception{
        //查看是否已经请假
        LeaveApproval leaveApproval = new LeaveApproval();
        leaveApproval.setStudentid(Integer.parseInt(studentId));
        leaveApproval.setLeavereason(leaveReason);
        //leaveApproval.setCourseid(Integer.parseInt(courseId));
        leaveapprovalService.save(leaveApproval);
        JsonResult<LeaveApproval> result = new JsonResult<>(ResultEnum.NORMAL.val(),"请假申请成功");
        result.setData(leaveApproval);
        return result;
    }
}