package com.xiezhenyu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.model.RegistrationDo;
import com.xiezhenyu.service.IRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tim
 */
@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private IRegistrationService registrationService;

    @PostMapping("/add")
    public CommonResult add(@RequestBody RegistrationDo registrationDo){
        boolean result = registrationService.add(registrationDo);
        if(result){
            return CommonResult.successCommonResult("签到成功");
        }else {
            return CommonResult.errorCommonResult("签到失败，今天已经签到。");
        }
    }

    @GetMapping("/isTodayRegistration/{id}")
    public CommonResult isTodayRegistration(@PathVariable("id")Long id){
        boolean result = registrationService.isTodayRegistration(id);
        if(result){
            return CommonResult.successCommonResult("今天未签到");
        }else {
            return CommonResult.errorCommonResult("今天已经签到");
        }
    }

    @GetMapping("/list/{id}/{limit}/{offset}")
    public CommonResult list(@PathVariable("id")Long id, @PathVariable("limit")Integer limit, @PathVariable("offset")Integer offset){
        Page<RegistrationDo> list = registrationService.list(id, limit, offset);
        return CommonResult.successCommonResult(list,"查询成功");
    }

    @GetMapping("/byDate/{id}/{startDate}/{endDate}")
    public CommonResult selectByDate(@PathVariable("id")Long id,@PathVariable("startDate")String startDate,@PathVariable("endDate")String endDate){
        List<RegistrationDo> registrationDos = registrationService.selectByDate(id, startDate, endDate);
        return CommonResult.successCommonResult(registrationDos,"查询成功");
    }

    @GetMapping("/count/{id}")
    public CommonResult countByUserId(@PathVariable("id")Long id){
        Integer count = registrationService.countByUserId(id);
        return CommonResult.successCommonResult(count,"查询成功");
    }

}
