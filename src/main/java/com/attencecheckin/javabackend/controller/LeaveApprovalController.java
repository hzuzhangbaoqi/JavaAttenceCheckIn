
package com.attencecheckin.javabackend.controller;

import com.attencecheckin.javabackend.common.enumer.ResultEnum;
import com.attencecheckin.javabackend.service.LeaveApprovalService;
import com.attencecheckin.javabackend.common.JsonResult;
import com.attencecheckin.javabackend.entity.LeaveApproval;
import com.attencecheckin.javabackend.service.LeaveApprovalService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public JsonResult leaveCourse(Integer studentId,
                                           String leaveReason,
                                           String leavedate,
                                                 String courseids) throws Exception{
        //查看是否已经请假
        List<Integer> courseidList = Arrays.asList(courseids.split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
        for(Integer courseid:courseidList){
            LeaveApproval byDateAndcourseid = leaveapprovalService.findByDateAndcourseid(studentId, leavedate, courseid);
            if(byDateAndcourseid!=null){
                JsonResult<Object> result = new JsonResult<>(ResultEnum.FAIL,leavedate +"已经存在请假记录");
                return result;
            }
        }

        for(Integer courseid:courseidList){

            LeaveApproval leaveApproval = new LeaveApproval();
            leaveApproval.setStudentid(studentId);
            leaveApproval.setStatus(0);
            leaveApproval.setLeavereason(leaveReason);
            leaveApproval.setCourseid(courseid);
            leaveApproval.setLeavesubtime(new Date());
            leaveApproval.setLeavedate(leavedate);
            LeaveApproval byDateAndcourseid = leaveapprovalService.findByDateAndcourseid(studentId, leavedate, courseid);
            if(byDateAndcourseid!=null){
                JsonResult<Object> result = new JsonResult<>(ResultEnum.FAIL,leavedate +"已经存在请假记录");
                return result;
            }
            leaveapprovalService.save(leaveApproval);
        }
        JsonResult<LeaveApproval> result = new JsonResult<>(ResultEnum.NORMAL.val(),"请假申请成功");
        return result;
    }

    //教师查看请假列表
    @RequestMapping("/showleaveCourseByTeacherid")
    @ApiOperation(value = "showleaveCourseByTeacherid", notes = "教师查看请假列表")
    public List<Map<String,Object>> showleaveCourseByTeacherid(Integer teacherid){
        List<Map<String,Object>> leaveApprovals = leaveapprovalService.showleaveCourseByTeacherid(teacherid);
        return leaveApprovals;
    }

    @RequestMapping("/agreeleaveCourseByTeacherid")
    @ApiOperation(value = "agreeleaveCourseByTeacherid", notes = "教师同意请假列表")
    public JsonResult agreeleaveCourseByTeacherid(Integer teacherid,String ids,String isAgree){
        List<Integer> idList = Arrays.asList(ids.split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
        int agree = leaveapprovalService.agreeleaveCourseByTeacherid(teacherid, idList,Integer.parseInt(isAgree));
        JsonResult result = new JsonResult(ResultEnum.NORMAL.val(), "同意请假成功");
        result.setData(agree);
        //TODO 这里修改已经迟到的记录


        return result;
    }
    @RequestMapping("/getCourseByStudent")
    @ApiOperation(value = "getCourseByStudent", notes = "根据学生id获取自身的请假记录")
    public JsonResult getCourseByDate(Integer studentid,String status)  {
        List<Integer> statuList = Arrays.asList(status.split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());

        List<Map<String,Object>>  courseByStudent = leaveapprovalService.getCourseByStudent(studentid, statuList);
        JsonResult result = new JsonResult(ResultEnum.NORMAL.val(), "获取自身的请假记录成功");
        result.setData(courseByStudent);
        return result;
    }
}