package com.xiezhenyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.mapper.RoleMapper;
import com.xiezhenyu.model.admin.Role;
import com.xiezhenyu.model.admin.UserManage;
import com.xiezhenyu.service.LoginService;
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
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private LoginService loginService;

    @Override
    public Role getRoleById(Long id) {
        Role role = roleMapper.selectById(id);
        return role;
    }

    @Override
    public Page<Role> getRolePage(Integer pageNo, Integer pageSize, String name) {
        Page<Role> rolePage = roleMapper.selectPage(new Page<>(pageNo, pageSize), new QueryWrapper<Role>().like("name",name));
        return rolePage;
    }

    @Override
    public void addRole(Role role) {
        role.setCtime(new Date());
        role.setUtime(new Date());
        roleMapper.insert(role);
    }

    @Override
    public void updateRole(Role role) {
        role.setUtime(new Date());
        roleMapper.updateById(role);
    }

    @Override
    public boolean deleteRole(Role role) {
        List<UserManage> list = loginService.getRoleId(role.getId());
        if(list!=null && list.size()!=0){
            return false;
        }else {
            roleMapper.deleteById(role.getId());
            return true;
        }
    }
}
