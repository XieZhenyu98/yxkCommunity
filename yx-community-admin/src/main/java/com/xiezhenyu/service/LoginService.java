package com.xiezhenyu.service;

import com.xiezhenyu.model.admin.UserManage;
import com.xiezhenyu.response.CommonResult;

import java.util.List;

/**
 * @author Tim
 * @date 2021/4/13
 */
public interface LoginService {

    /**
     * 管理员登录
     * @param userManage
     * @return
     */
    CommonResult login(UserManage userManage);

    /**
     * 通过权限id获取管理员
     * @param roleId
     * @return
     */
    List<UserManage> getRoleId(Long roleId);

}
