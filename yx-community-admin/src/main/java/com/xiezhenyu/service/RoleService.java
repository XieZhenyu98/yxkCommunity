package com.xiezhenyu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.model.admin.Role;

/**
 * @author Tim
 */
public interface RoleService {

    /**
     * 通过id获取权限
     * @param id
     * @return
     */
    Role getRoleById(Long id);

    /**
     * 分页获取权限列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Role> getRolePage(Integer pageNo, Integer pageSize, String name);

    /**
     * 添加权限
     * @param role
     */
    void addRole(Role role);

    /**
     * 修改权限
     * @param role
     */
    void updateRole(Role role);

    /**
     * 删除权限
     * @param role
     * @return
     */
    boolean deleteRole(Role role);
}
