package com.xiezhenyu.service;

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

}
