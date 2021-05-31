package com.xiezhenyu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.model.CommonConfigDo;
import com.xiezhenyu.query.CommonConfigQuery;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.service.ICommonConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tim
 * @date 2021/5/30
 */
@Api("系统环境变量配置")
@RestController
@RequestMapping("/commonConfig")
public class CommonConfigController {

    @Autowired
    private ICommonConfigService commonConfigService;

    @ApiOperation("系统环境变量分页")
    @PostMapping("/page")
    public CommonResult getPage(@ApiParam("系统环境变量实体类") @RequestBody CommonConfigQuery commonConfigQuery) {
        Page<CommonConfigDo> page = commonConfigService.getPage(commonConfigQuery);
        return CommonResult.successCommonResult(page,"查询成功");
    }

    @ApiOperation("添加系统环境变量")
    @PostMapping("/add")
    public CommonResult addCommonConfig(@ApiParam("系统环境变量实体类") @RequestBody CommonConfigDo commonConfigDo) {
        commonConfigService.addCommonConfig(commonConfigDo);
        return CommonResult.successCommonResult("添加成功");
    }

    @ApiOperation("系统环境变量修改")
    @PostMapping("/update")
    public CommonResult updateCommonConfig(@ApiParam("系统环境变量实体类") @RequestBody CommonConfigDo commonConfigDo) {
        commonConfigService.updateCommonConfig(commonConfigDo);
        return CommonResult.successCommonResult("修改成功");
    }

    @ApiOperation("系统环境变量删除")
    @PostMapping("/delete")
    public CommonResult deleteCommonConfig(@ApiParam("系统环境变量实体类") @RequestBody CommonConfigDo commonConfigDo) {
        commonConfigService.deleteCommonConfig(commonConfigDo);
        return CommonResult.successCommonResult("删除成功");
    }
}
