package com.xiezhenyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.mapper.LoginMapper;
import com.xiezhenyu.model.admin.UserManage;
import com.xiezhenyu.query.UserManageQuery;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.service.LoginService;
import com.xiezhenyu.service.RoleService;
import com.xiezhenyu.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Tim
 * @date 2021/4/13
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private RoleService roleService;

    @Override
    public CommonResult login(UserManage userManage) {
        UserManage userManageDB = loginMapper.selectOne(new QueryWrapper<UserManage>().eq("name",userManage.getName()));
        if(userManageDB==null||!userManageDB.getPassword().equals(userManage.getPassword())){
            return CommonResult.errorCommonResult("帐号或者密码错误");
        }
        userManageDB.setLastLoginTime(new Date());
        loginMapper.updateById(userManageDB);
        Map<String, Object> resMap = new HashMap<>();
        Map<String,String> payload = new HashMap<>();
        payload.put("id",userManageDB.getId().toString());
        payload.put("username",userManageDB.getName());
        payload.put("roleId",userManageDB.getRoleId().toString());
        String token = JwtUtils.getToken(payload);
        resMap.put("token",token);
        resMap.put("userManage",userManageDB);
        return CommonResult.successCommonResult(resMap,"登录成功");
    }

    @Override
    public List<UserManage> getRoleId(Long roleId) {
        List<UserManage> list = loginMapper.selectList(new QueryWrapper<UserManage>().eq("role_id", roleId));
        return list;
    }

    @Override
    public Page<UserManage> getPage(UserManageQuery userManageQuery) {
        Page<UserManage> userManagePage = null;
        if(userManageQuery.getRoleId()==null){
            userManagePage = loginMapper.selectPage(new Page<>(userManageQuery.getPageNo(), userManageQuery.getPageSize()),
                    new QueryWrapper<UserManage>()
                            .like("name", userManageQuery.getName()));
        } else{
            userManagePage = loginMapper.selectPage(new Page<>(userManageQuery.getPageNo(), userManageQuery.getPageSize()),
                    new QueryWrapper<UserManage>()
                            .like("name", userManageQuery.getName())
                            .like("role_id", userManageQuery.getRoleId()));
        }
        for (UserManage userManage : userManagePage.getRecords()){
            userManage.setRole(roleService.getRoleById(userManage.getRoleId()));
        }
        return userManagePage;
    }

    @Override
    public boolean addUserManage(UserManage userManage) {
        userManage.setCreateTime(new Date());
        userManage.setLastLoginTime(new Date());
        loginMapper.insert(userManage);
        return true;
    }

    @Override
    public boolean updateUserManage(UserManage userManage) {
        loginMapper.updateById(userManage);
        return true;
    }

    @Override
    public boolean deleteUserManage(UserManage userManage) {
        loginMapper.deleteById(userManage.getId());
        return true;
    }
}
