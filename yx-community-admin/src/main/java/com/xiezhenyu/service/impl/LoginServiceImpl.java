package com.xiezhenyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiezhenyu.mapper.LoginMapper;
import com.xiezhenyu.model.admin.UserManage;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.service.LoginService;
import com.xiezhenyu.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tim
 * @date 2021/4/13
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public CommonResult login(UserManage userManage) {
        UserManage userManageDB = loginMapper.selectOne(new QueryWrapper<UserManage>().eq("name",userManage.getName()));
        if(userManageDB==null||userManageDB.getPassword()!=userManage.getPassword()){
            return CommonResult.errorCommonResult("帐号或者密码错误");
        }
        userManageDB.setLastLoginTime(new Date());
        loginMapper.updateById(userManageDB);
        ArrayList<Object> list = new ArrayList<>();
        Map<String,String> payload = new HashMap<>();
        payload.put("id",userManageDB.getId().toString());
        payload.put("username",userManageDB.getName());
        payload.put("level",userManageDB.getLevel().toString());
        String token = JwtUtils.getToken(payload);
        list.add(token);
        list.add(userManageDB);
        return CommonResult.successCommonResult(list,"登录成功");
    }
}
