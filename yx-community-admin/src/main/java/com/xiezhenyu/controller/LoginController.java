package com.xiezhenyu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.model.admin.UserManage;
import com.xiezhenyu.query.UserManageQuery;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.service.LoginService;
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
 * @date 2021/4/13
 */
@Api("管理员")
@RestController
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "管理员登录",notes = "管理员登录")
    @PostMapping("/login")
    public CommonResult login(@ApiParam("管理员登录") @RequestBody UserManage userManage){
        return loginService.login(userManage);
    }

    @ApiOperation("管理员分页")
    @PostMapping("/userManage/page")
    public CommonResult getPage(@ApiParam("查询实体类") @RequestBody UserManageQuery userManageQuery) {
        Page<UserManage> page = loginService.getPage(userManageQuery);
        return CommonResult.successCommonResult(page,"查询成功");
    }

    @ApiOperation("添加管理员")
    @PostMapping("/userManage/add")
    public CommonResult addUserManage(@ApiParam("管理员实体类") @RequestBody UserManage userManage) {
        loginService.addUserManage(userManage);
        return CommonResult.successCommonResult("添加成功");
    }

    @ApiOperation("修改管理员")
    @PostMapping("/userManage/update")
    public CommonResult updateUserManage(@ApiParam("管理员实体类") @RequestBody UserManage userManage) {
        loginService.updateUserManage(userManage);
        return CommonResult.successCommonResult("修改成功");
    }

    @ApiOperation("删除管理员")
    @PostMapping("/userManage/delete")
    public CommonResult deleteUserManage(@ApiParam("管理员实体类") @RequestBody UserManage userManage) {
        loginService.deleteUserManage(userManage);
        return CommonResult.successCommonResult("删除成功");
    }
}
