package com.xiezhenyu.controller;

import com.xiezhenyu.common.response.CommonResult;
import com.xiezhenyu.model.SonModuleDo;
import com.xiezhenyu.service.ISonModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * @author Tim
 */
@RestController
@RequestMapping("/sonModule")
public class SonModuleController {

    @Autowired
    private ISonModuleService sonModuleService;

    @PostMapping("/add")
    public CommonResult add(@RequestBody SonModuleDo sonModuleDo){
        boolean result = sonModuleService.insert(sonModuleDo);
        if(result){
            return CommonResult.successCommonResult("添加成功");
        }else {
            return CommonResult.errorCommonResult("添加失败");
        }
    }

    @GetMapping("/list/{limit}/{offset}")
    public CommonResult selectList(@PathVariable("limit")Integer limit,@PathVariable("offset")Integer offset){
        ArrayList<SonModuleDo> sonModuleDos = sonModuleService.selectList(limit,offset);
        return CommonResult.successCommonResult(sonModuleDos,"查询成功");
    }

    @PutMapping("/update")
    public CommonResult update(@RequestBody SonModuleDo sonModuleDo){
        boolean result = sonModuleService.update(sonModuleDo);
        if(result){
            return CommonResult.successCommonResult("修改成功");
        }else{
            return CommonResult.errorCommonResult("修改失败");
        }
    }

    @GetMapping("/{id}")
    public CommonResult selectById(@PathVariable("id")Long id){
        SonModuleDo sonModuleDo = sonModuleService.selectById(id);
        return CommonResult.successCommonResult(sonModuleDo,"查询成功");
    }

    @DeleteMapping("/delete/{id}")
    public CommonResult deleteById(@PathVariable("id")Long id){
        boolean result = sonModuleService.deleteById(id);
        if(result){
            return CommonResult.successCommonResult("删除成功");
        }else {
            return CommonResult.errorCommonResult("删除失败");
        }
    }
}
