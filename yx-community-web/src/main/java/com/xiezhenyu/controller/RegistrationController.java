package com.xiezhenyu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.model.RegistrationDo;
import com.xiezhenyu.service.IRegistrationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tim
 */
@Api(tags = "签到")
@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private IRegistrationService registrationService;

    @ApiOperation(value = "添加签到",notes = "添加签到")
    @PostMapping("/add")
    public CommonResult add(@ApiParam(value = "签到实体类") @RequestBody RegistrationDo registrationDo){
        boolean result = registrationService.add(registrationDo);
        if(result){
            return CommonResult.successCommonResult("签到成功");
        }else {
            return CommonResult.errorCommonResult("签到失败，今天已经签到。");
        }
    }

    @ApiOperation(value = "今天是否签到",notes = "查询今天是否签到")
    @GetMapping("/isTodayRegistration/{id}")
    public CommonResult isTodayRegistration(@ApiParam(value = "") @PathVariable("id")Long id){
        boolean result = registrationService.isTodayRegistration(id);
        if(result){
            return CommonResult.successCommonResult("今天未签到");
        }else {
            return CommonResult.errorCommonResult("今天已经签到");
        }
    }

    @ApiOperation(value = "用户ID查询签到列表",notes = "通过用户ID分页查询签到列表")
    @GetMapping("/list/{id}/{limit}/{offset}")
    public CommonResult list(@ApiParam(value = "用户ID") @PathVariable("id")Long id,
                             @ApiParam(value = "第几页") @PathVariable("limit")Integer limit,
                             @ApiParam(value = "偏移量") @PathVariable("offset")Integer offset){
        Page<RegistrationDo> list = registrationService.list(id, limit, offset);
        return CommonResult.successCommonResult(list,"查询成功");
    }

    @ApiOperation(value = "时间段签到情况",notes = "通过用户的ID查询该用户这段时间的签到情况")
    @GetMapping("/byDate/{id}/{startDate}/{endDate}")
    public CommonResult selectByDate(@ApiParam(value = "用户ID") @PathVariable("id")Long id,
                                     @ApiParam(value = "开始时间") @PathVariable("startDate")String startDate,
                                     @ApiParam(value = "结束时间") @PathVariable("endDate")String endDate){
        List<RegistrationDo> registrationDos = registrationService.selectByDate(id, startDate, endDate);
        return CommonResult.successCommonResult(registrationDos,"查询成功");
    }

    @ApiOperation(value = "签到总数",notes = "通过用户ID查询该用户签到的总次数")
    @GetMapping("/count/{id}")
    public CommonResult countByUserId(@ApiParam(value = "用户ID") @PathVariable("id")Long id){
        Integer count = registrationService.countByUserId(id);
        return CommonResult.successCommonResult(count,"查询成功");
    }

}
