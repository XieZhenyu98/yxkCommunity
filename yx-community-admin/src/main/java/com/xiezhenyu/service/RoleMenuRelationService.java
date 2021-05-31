package com.xiezhenyu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.model.admin.RoleMenuRelation;
import com.xiezhenyu.query.RoleMenuRelationQuery;

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

    /**
     * 获取权限管理
     * @param roleMenuRelationQuery
     * @return
     */
    Page<RoleMenuRelation> getRoleMenuRelationPage(RoleMenuRelationQuery roleMenuRelationQuery);

    /**
     * 添加权限管理
     * @param roleMenuRelation
     * @return
     */
    boolean addRoleMenuRelation(RoleMenuRelation roleMenuRelation);

    /**
     * 修改权限管理
     * @param roleMenuRelation
     * @return
     */
    boolean updateRoleMenuRelation(RoleMenuRelation roleMenuRelation);

    /**
     * 删除权限管理
     * @param roleMenuRelation
     * @return
     */
    boolean deleteRoleMenuRelation(RoleMenuRelation roleMenuRelation);

}
