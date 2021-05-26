package com.xiezhenyu.service;

import com.xiezhenyu.model.admin.RoleMenuRelation;

import java.util.List;

/**
 * @author Tim
 */
public interface RoleMenuRelationService {

    /**
     * 通过权限id获取
     * @param roleId
     * @return
     */
    List<RoleMenuRelation> getRoleMenuRelationByRoleId(Long roleId);

}
