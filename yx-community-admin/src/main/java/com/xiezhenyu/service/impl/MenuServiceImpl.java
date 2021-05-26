package com.xiezhenyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiezhenyu.mapper.MenuMapper;
import com.xiezhenyu.model.admin.Menu;
import com.xiezhenyu.model.admin.RoleMenuRelation;
import com.xiezhenyu.service.MenuService;
import com.xiezhenyu.service.RoleMenuRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tim
 * @date 2021/5/26
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMenuRelationService roleMenuRelationService;

    @Override
    public List<Menu> getAllMenu() {
        List<Menu> parentMenu = menuMapper.selectList(new QueryWrapper<Menu>().eq("parent_id", 0).orderByDesc("sort"));
        for(Menu menu : parentMenu) {
            List<Menu> nextMenu = menuMapper.selectList(new QueryWrapper<Menu>().eq("parent_id", menu.getId()).orderByDesc("sort"));
            menu.setNextMenus(nextMenu);
        }
        return parentMenu;
    }

    @Override
    public List<Menu> getMenuByRoleId(Long roleId) {
        List<RoleMenuRelation> roleMenuRelations = roleMenuRelationService.getRoleMenuRelationByRoleId(roleId);
        List<Long> menuIds = new ArrayList<>();
        for (RoleMenuRelation roleMenuRelation : roleMenuRelations) {
            menuIds.add(roleMenuRelation.getMenuId());
        }
        List<Menu> menus = menuMapper.selectList(
                new QueryWrapper<Menu>()
                        .eq("hidden", 0)
                        .orderByDesc("sort")
                        .eq("parent_id", 0)
                        .in("id",menuIds));
        for (Menu menu : menus) {
            List<Menu> nextMenus = menuMapper.selectList(
                    new QueryWrapper<Menu>()
                            .eq("hidden", 0)
                            .orderByDesc("sort")
                            .eq("parent_id", menu.getId())
                            .in("id", menuIds));
            menu.setNextMenus(nextMenus);
        }
        return menus;
    }
}
