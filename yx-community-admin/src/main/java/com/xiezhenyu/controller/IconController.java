package com.xiezhenyu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.model.admin.Icon;
import com.xiezhenyu.query.IconQuery;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.service.IconService;
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
@Api("图标")
@RestController
@RequestMapping("/admin/icon")
public class IconController {

    @Autowired
    private IconService iconService;

    @ApiOperation("图标分页")
    @PostMapping("/page")
    public CommonResult getPage(@ApiParam("图标查询实体类") @RequestBody IconQuery iconQuery) {
        Page<Icon> page = iconService.getPage(iconQuery);
        return CommonResult.successCommonResult(page,"查询成功");
    }

    @ApiOperation("添加图标")
    @PostMapping("/add")
    public CommonResult addIcon(@ApiParam("图标实体类") @RequestBody Icon icon) {
        iconService.addIcon(icon);
        return CommonResult.successCommonResult("添加成功");
    }

    @ApiOperation("修改图标")
    @PostMapping("/update")
    public CommonResult updateIcon(@ApiParam("图标实体类") @RequestBody Icon icon) {
        iconService.updateIcon(icon);
        return CommonResult.successCommonResult("修改成功");
    }

    @ApiOperation("删除图标")
    @PostMapping("/delete")
    public CommonResult deleteIcon(@ApiParam("图标实体类") @RequestBody Icon icon) {
        iconService.deleteIcon(icon);
        return CommonResult.successCommonResult("删除成功");
    }

}
