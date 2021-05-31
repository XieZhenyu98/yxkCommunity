package com.xiezhenyu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.model.admin.RoleMenuRelation;
import com.xiezhenyu.query.RoleMenuRelationQuery;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.service.RoleMenuRelationService;
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
@Api("权限管理")
@RestController
@RequestMapping("/admin/roleMenu")
public class RoleMenuController {
    @Autowired
    private RoleMenuRelationService roleMenuRelationService;

    @ApiOperation("查询列表")
    @PostMapping("/list")
    public CommonResult getRoleMenuRelationPage(@ApiParam("查询实体类") @RequestBody RoleMenuRelationQuery roleMenuRelationQuery) {
        Page<RoleMenuRelation> roleMenuRelationPage = roleMenuRelationService.getRoleMenuRelationPage(roleMenuRelationQuery);
        return CommonResult.successCommonResult(roleMenuRelationPage,"查询成功");
    }

    @ApiOperation("添加权限")
    @PostMapping("/add")
    public CommonResult addRoleMenuRelation(@ApiParam("实体类") @RequestBody RoleMenuRelation roleMenuRelation) {
        roleMenuRelationService.addRoleMenuRelation(roleMenuRelation);
        return CommonResult.successCommonResult("插入成功");
    }

    @ApiOperation("修改权限")
    @PostMapping("/update")
    public CommonResult updateRoleMenuRelation(@ApiParam("实体类") @RequestBody RoleMenuRelation roleMenuRelation) {
        roleMenuRelationService.updateRoleMenuRelation(roleMenuRelation);
        return CommonResult.successCommonResult("修改成功");
    }

    @ApiOperation("删除权限")
    @PostMapping("/delete")
    public CommonResult deleteRoleMenuRelation(@ApiParam("实体类") @RequestBody RoleMenuRelation roleMenuRelation) {
        roleMenuRelationService.deleteRoleMenuRelation(roleMenuRelation);
        return CommonResult.successCommonResult("删除成功");
    }
}
