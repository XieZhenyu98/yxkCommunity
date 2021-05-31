package com.xiezhenyu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.model.admin.UserManage;
import com.xiezhenyu.query.UserManageQuery;
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

    /**
     * 获取管理员分页
     * @param userManageQuery
     * @return
     */
    Page<UserManage> getPage(UserManageQuery userManageQuery);

    /**
     * 添加管理员
     * @param userManage
     * @return
     */
    boolean addUserManage(UserManage userManage);

    /**
     * 修改管理员
     * @param userManage
     * @return
     */
    boolean updateUserManage(UserManage userManage);

    /**
     * 删除管理员
     * @param userManage
     * @return
     */
    boolean deleteUserManage(UserManage userManage);
}
