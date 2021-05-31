package com.xiezhenyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.mapper.RoleMenuRelationMapper;
import com.xiezhenyu.model.admin.RoleMenuRelation;
import com.xiezhenyu.query.RoleMenuRelationQuery;
import com.xiezhenyu.service.MenuService;
import com.xiezhenyu.service.RoleMenuRelationService;
import com.xiezhenyu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Tim
 * @date 2021/5/26
 */
@Service
public class RoleMenuServiceRelationImpl implements RoleMenuRelationService {

    @Autowired
    private RoleMenuRelationMapper roleMenuRelationMapper;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    @Override
    public List<RoleMenuRelation> getRoleMenuRelationByRoleId(Long roleId) {
        List<RoleMenuRelation> list = roleMenuRelationMapper.selectList(new QueryWrapper<RoleMenuRelation>().eq("role_id", roleId));
        return list;
    }

    @Override
    public Page<RoleMenuRelation> getRoleMenuRelationPage(RoleMenuRelationQuery roleMenuRelationQuery) {
        Page<RoleMenuRelation> roleMenuRelationPage = null;
        if(roleMenuRelationQuery.getMenu().getId()!=null && roleMenuRelationQuery.getRole().getId()!=null){
            roleMenuRelationPage = roleMenuRelationMapper.selectPage(
                    new Page<>(roleMenuRelationQuery.getPageNo(), roleMenuRelationQuery.getPageSize()),
                    new QueryWrapper<RoleMenuRelation>()
                            .eq("role_id",roleMenuRelationQuery.getRole().getId())
                            .eq("menu_id",roleMenuRelationQuery.getMenu().getId()));
        }
        if(roleMenuRelationQuery.getMenu().getId()==null && roleMenuRelationQuery.getRole().getId()!=null){
            roleMenuRelationPage = roleMenuRelationMapper.selectPage(
                    new Page<>(roleMenuRelationQuery.getPageNo(), roleMenuRelationQuery.getPageSize()),
                    new QueryWrapper<RoleMenuRelation>()
                            .eq("role_id",roleMenuRelationQuery.getRole().getId()));
        }
        if(roleMenuRelationQuery.getMenu().getId()!=null && roleMenuRelationQuery.getRole().getId()==null){
            roleMenuRelationPage = roleMenuRelationMapper.selectPage(
                    new Page<>(roleMenuRelationQuery.getPageNo(), roleMenuRelationQuery.getPageSize()),
                    new QueryWrapper<RoleMenuRelation>()
                            .eq("menu_id",roleMenuRelationQuery.getMenu().getId()));
        }
        if(roleMenuRelationQuery.getMenu().getId()==null && roleMenuRelationQuery.getRole().getId()==null){
            roleMenuRelationPage = roleMenuRelationMapper.selectPage(
                    new Page<>(roleMenuRelationQuery.getPageNo(), roleMenuRelationQuery.getPageSize()),null);
        }
        for(RoleMenuRelation roleMenuRelation : roleMenuRelationPage.getRecords()) {
            roleMenuRelation.setRole(roleService.getRoleById(roleMenuRelation.getRoleId()));
            roleMenuRelation.setMenu(menuService.getById(roleMenuRelation.getMenuId()));
        }
        return roleMenuRelationPage;
    }

    @Override
    public boolean addRoleMenuRelation(RoleMenuRelation roleMenuRelation) {
        roleMenuRelation.setCtime(new Date());
        roleMenuRelation.setUtime(new Date());
        roleMenuRelation.setMenuId(roleMenuRelation.getMenu().getId());
        roleMenuRelation.setRoleId(roleMenuRelation.getRole().getId());
        roleMenuRelationMapper.insert(roleMenuRelation);
        return true;
    }

    @Override
    public boolean updateRoleMenuRelation(RoleMenuRelation roleMenuRelation) {
        roleMenuRelation.setMenuId(roleMenuRelation.getMenu().getId());
        roleMenuRelation.setRoleId(roleMenuRelation.getRole().getId());
        roleMenuRelation.setUtime(new Date());
        roleMenuRelationMapper.updateById(roleMenuRelation);
        return true;
    }

    @Override
    public boolean deleteRoleMenuRelation(RoleMenuRelation roleMenuRelation) {
        roleMenuRelationMapper.deleteById(roleMenuRelation.getId());
        return true;
    }
}
