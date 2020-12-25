package com.xiezhenyu.controller;

import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.entity.SonModuleVo;
import com.xiezhenyu.service.ISonModuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * @author Tim
 */
@Api(tags = "子模块")
@RestController
@RequestMapping("/sonModule")
public class SonModuleController {

    @Autowired
    private ISonModuleService sonModuleService;

    @ApiOperation(value = "添加子模块",notes = "添加子模块")
    @PostMapping("/add")
    public CommonResult add(@ApiParam(value = "子模块实体类") @RequestBody SonModuleVo sonModuleVo){
        boolean result = sonModuleService.insert(sonModuleVo);
        if(result){
            return CommonResult.successCommonResult("添加成功");
        }else {
            return CommonResult.errorCommonResult("添加失败");
        }
    }

    @ApiOperation(value = "子模块列表",notes = "分页查询子模块列表")
    @GetMapping("/list/{limit}/{offset}")
    public CommonResult selectList(@ApiParam(value = "第几条") @PathVariable("limit")Integer limit,
                                   @ApiParam(value = "偏移量") @PathVariable("offset")Integer offset){
        ArrayList<SonModuleVo> sonModuleVos = sonModuleService.selectList(limit,offset);
        return CommonResult.successCommonResult(sonModuleVos,"查询成功");
    }

    @ApiOperation(value = "修改子模块",notes = "修改子模块")
    @PutMapping("/update")
    public CommonResult update(@ApiParam(value = "子模块实体类") @RequestBody SonModuleVo sonModuleVo){
        boolean result = sonModuleService.update(sonModuleVo);
        if(result){
            return CommonResult.successCommonResult("修改成功");
        }else{
            return CommonResult.errorCommonResult("修改失败");
        }
    }

    @ApiOperation(value = "子模块ID获得子模块",notes = "通过子模块ID获得子模块")
    @GetMapping("/{id}")
    public CommonResult selectById(@ApiParam(value = "子模块ID") @PathVariable("id")Long id){
        SonModuleVo sonModuleVo = sonModuleService.selectById(id);
        return CommonResult.successCommonResult(sonModuleVo,"查询成功");
    }

    @ApiOperation(value = "删除子模块",notes = "通过子模块ID删除子模块")
    @DeleteMapping("/delete/{id}")
    public CommonResult deleteById(@ApiParam(value = "子模块ID") @PathVariable("id")Long id){
        boolean result = sonModuleService.deleteById(id);
        if(result){
            return CommonResult.successCommonResult("删除成功");
        }else {
            return CommonResult.errorCommonResult("删除失败");
        }
    }
}
