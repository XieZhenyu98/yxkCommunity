package com.xiezhenyu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.query.UserQuery;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.entity.UserVo;
import com.xiezhenyu.model.UserDo;
import com.xiezhenyu.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tim
 */
@Api(tags = "用户")
@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "添加用户",notes = "添加用户")
    @PostMapping("/add")
    public CommonResult register(@ApiParam(value = "用户实体类") @RequestBody UserDo userDo){
        boolean result = userService.register(userDo);
        return CommonResult.successCommonResult(result,"注册成功");
    }

    @ApiOperation(value = "用户登录",notes = "用户登录")
    @PostMapping("/login")
    public CommonResult login(@ApiParam(value = "用户实体类") @RequestBody UserDo userDo){
        return userService.login(userDo);
    }

    @ApiOperation(value = "用户ID查询用户",notes = "通过用户ID查询用户")
    @GetMapping("/{id}")
    public CommonResult getUserById(@ApiParam(value = "用户ID") @PathVariable("id") Long id){
        UserDo user = userService.getUserById(id);
        UserVo userVo = user.toUserVo();
        return CommonResult.successCommonResult(userVo,"获取成功");
    }

    @ApiOperation(value = "修改用户",notes = "修改用户")
    @PutMapping("/update")
    public CommonResult update(@ApiParam(value = "用户实体类") @RequestBody UserDo userDo){
        UserDo user = userService.updateUser(userDo);
        return CommonResult.successCommonResult(user,"修改成功");
    }

    @ApiOperation(value = "修改用户密码",notes = "修改用户密码")
    @PutMapping("/updatePassword")
    public CommonResult updatePassword(@ApiParam(value = "用户实体类") @RequestBody UserDo userDo){
        boolean result = userService.updatePassword(userDo);
        if(result){
            return  CommonResult.successCommonResult("修改成功");
        }else{
            return CommonResult.errorCommonResult("原密码错误，修改失败！");
        }
    }

    @ApiOperation(value = "通过经验排序查询用户列表",notes = "通过经验由高到低查询用户列表")
    @GetMapping("/userListByEx")
    public CommonResult userListByEx(){
        Page<UserVo> userVoPage = userService.userListByEx();
        return CommonResult.successCommonResult(userVoPage,"查询成功");
    }

    @ApiOperation("查询用户列表")
    @PostMapping("/userList")
    public CommonResult userList(@ApiParam("用户实体类") @RequestBody UserQuery userQuery) {
        Page<UserDo> userDoPage = userService.userList(userQuery);
        return CommonResult.successCommonResult(userDoPage,"查询成功");
    }

    @ApiOperation("删除用户")
    @PostMapping("/delete")
    public CommonResult deleteUser(@ApiParam("用户实体类") @RequestBody UserDo userDo){
        userService.deleteUser(userDo);
        return CommonResult.successCommonResult("删除成功");
    }

    @ApiOperation("删除用户")
    @PostMapping("/recovery")
    public CommonResult recoveryUser(@ApiParam("用户实体类") @RequestBody UserDo userDo){
        userService.recoveryUser(userDo);
        return CommonResult.successCommonResult("恢复成功");
    }

    @ApiOperation("重置密码")
    @PostMapping("/resetPassword")
    public CommonResult resetPassword(@ApiParam("用户实体类") @RequestBody UserDo userDo) {
        userService.resetPassword(userDo);
        return CommonResult.successCommonResult("重置成功");
    }

    @ApiOperation("通过关键字查找用户")
    @GetMapping("/allByKeyword")
    public CommonResult getByKeywords(@ApiParam("关键字") String keywords) {
        List<UserDo> list = userService.getByKeywords(keywords);
        return CommonResult.successCommonResult(list,"查询成功");
    }
}
