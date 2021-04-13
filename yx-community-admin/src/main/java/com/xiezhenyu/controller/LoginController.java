package com.xiezhenyu.controller;

import com.xiezhenyu.model.admin.UserManage;
import com.xiezhenyu.response.CommonResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tim
 * @date 2021/4/13
 */
@RestController
@RequestMapping("/admin")
public class LoginController {

    @ApiOperation(value = "管理员登录",notes = "管理员登录")
    @PostMapping("/login")
    public CommonResult login(@ApiParam("管理员登录") @RequestBody UserManage userManage){
        return null;
    }
}
