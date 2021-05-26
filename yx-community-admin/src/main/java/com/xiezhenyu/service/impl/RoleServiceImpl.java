package com.xiezhenyu.service.impl;

import com.xiezhenyu.mapper.RoleMapper;
import com.xiezhenyu.model.admin.Role;
import com.xiezhenyu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tim
 * @date 2021/5/26
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role getRoleById(Long id) {
        Role role = roleMapper.selectById(id);
        return role;
    }
}
