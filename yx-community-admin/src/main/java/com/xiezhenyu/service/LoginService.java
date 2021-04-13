package com.xiezhenyu.service;

import com.xiezhenyu.model.admin.UserManage;
import com.xiezhenyu.response.CommonResult;

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
    public CommonResult login(UserManage userManage);

}
