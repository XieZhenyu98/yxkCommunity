package com.xiezhenyu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.query.FatherModuleQuery;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.entity.FatherModuleVo;
import com.xiezhenyu.model.FatherModuleDo;
import com.xiezhenyu.service.IFatherModuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tim
 */
@Api(tags = "父模块")
@RestController
@RequestMapping("/fatherModule")
public class FatherModuleController {

    @Autowired
    private IFatherModuleService fatherModuleService;

    @ApiOperation(value = "添加父模块",notes = "添加父模块")
    @PostMapping("/add")
    public CommonResult add(@ApiParam(value = "父模块实体类") @RequestBody FatherModuleDo fatherModuleDo){
        FatherModuleDo fatherModule = fatherModuleService.add(fatherModuleDo);
        return CommonResult.successCommonResult(fatherModule,"添加成功");
    }

    @ApiOperation(value = "删除父模块",notes = "通过父模块ID删除父模块")
    @DeleteMapping("/delete/{id}")
    public CommonResult deleteById(@ApiParam(value = "父模块ID") @PathVariable("id") Long id){
        boolean result = fatherModuleService.deleteById(id);
        if(result){
            return CommonResult.successCommonResult("删除成功");
        }else {
            return CommonResult.errorCommonResult("删除失败");
        }
    }

    @ApiOperation(value = "修改父模块",notes = "修改父模块")
    @PutMapping("/update")
    public CommonResult update(@ApiParam(value = "父模块实体类") @RequestBody FatherModuleDo fatherModuleDo){
        FatherModuleDo fatherModule = fatherModuleService.update(fatherModuleDo);
        return CommonResult.successCommonResult(fatherModule,"修改成功");
    }

    @ApiOperation(value = "获取父模块列表",notes = "分页获取父模块列表")
    @GetMapping("/select/{limit}/{offset}")
    public CommonResult selectList(@ApiParam(value = "第几页") @PathVariable("limit")Integer limit,
                                   @ApiParam(value = "偏移量") @PathVariable("offset")Integer offset){
        Page<FatherModuleVo> fatherModuleVoPage = fatherModuleService.selectList(limit, offset);
        return CommonResult.successCommonResult(fatherModuleVoPage,"查询成功");
    }

    @ApiOperation(value = "ID查询父模块",notes = "通过父模块ID获得父模块")
    @GetMapping("/select/{id}")
    public CommonResult selectById(@ApiParam(value = "") @PathVariable("id")Long id){
        FatherModuleDo fatherModule = fatherModuleService.selectById(id);
        return CommonResult.successCommonResult(fatherModule,"查询成功");
    }

    @ApiOperation("父板块列表分页")
    @PostMapping("/page")
    public CommonResult getPage(@ApiParam("查询实体类") @RequestBody FatherModuleQuery fatherModuleQuery) {
        Page<FatherModuleDo> page = fatherModuleService.getPage(fatherModuleQuery);
        return CommonResult.successCommonResult(page,"查询成功");
    }

    @ApiOperation("父板块删除")
    @PostMapping("/delete")
    public CommonResult deleteModule(@ApiParam("父板块实体类") @RequestBody FatherModuleDo fatherModuleDo) {
        boolean res = fatherModuleService.deleteModule(fatherModuleDo);
        if(res) {
            return CommonResult.successCommonResult("删除成功");
        }else {
            return CommonResult.errorCommonResult("删除失败，请先将改父板块下的子版块删除！");
        }
    }

    @ApiOperation("获取所有父板块")
    @GetMapping("/all")
    public CommonResult getAll() {
        List<FatherModuleDo> list = fatherModuleService.getAll();
        return CommonResult.successCommonResult(list,"查询成功");
    }

}
