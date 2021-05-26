package com.xiezhenyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiezhenyu.mapper.RoleMenuRelationMapper;
import com.xiezhenyu.model.admin.RoleMenuRelation;
import com.xiezhenyu.service.RoleMenuRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tim
 * @date 2021/5/26
 */
@Service
public class RoleMenuServiceRelationImpl implements RoleMenuRelationService {

    @Autowired
    private RoleMenuRelationMapper roleMenuRelationMapper;

    @Override
    public List<RoleMenuRelation> getRoleMenuRelationByRoleId(Long roleId) {
        List<RoleMenuRelation> list = roleMenuRelationMapper.selectList(new QueryWrapper<RoleMenuRelation>().eq("role_id", roleId));
        return list;
    }
}
