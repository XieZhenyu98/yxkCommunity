package com.xiezhenyu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.common.response.CommonResult;
import com.xiezhenyu.entity.UserVo;
import com.xiezhenyu.model.UserDo;
import com.xiezhenyu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tim
 */
@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/add")
    public CommonResult register(@RequestBody UserDo userDo){
        boolean result = userService.register(userDo);
        return CommonResult.successCommonResult(result,"注册成功");
    }

    @PostMapping("/login")
    public CommonResult login(@RequestBody UserDo userDo){
        return userService.login(userDo);
    }

    @GetMapping("/{id}")
    public CommonResult getUserById(@PathVariable("id") Long id){
        UserDo user = userService.getUserById(id);
        return CommonResult.successCommonResult(user,"获取成功");
    }

    @PutMapping("/update")
    public CommonResult update(@RequestBody UserDo userDo){
        UserDo user = userService.updateUser(userDo);
        return CommonResult.successCommonResult(user,"修改成功");
    }

    @PutMapping("/updatePassword")
    public CommonResult updatePassword(@RequestBody UserDo userDo){
        boolean result = userService.updatePassword(userDo);
        if(result){
            return  CommonResult.successCommonResult("修改成功");
        }else{
            return CommonResult.errorCommonResult("原密码错误，修改失败！");
        }
    }

    @GetMapping("/userListByEx")
    public CommonResult userListByEx(){
        Page<UserVo> userVoPage = userService.userListByEx();
        return CommonResult.successCommonResult(userVoPage,"查询成功");
    }
}
