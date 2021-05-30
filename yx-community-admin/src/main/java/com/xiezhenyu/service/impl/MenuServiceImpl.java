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
import java.util.Date;
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

    @Override
    public List<Menu> getByParentId(Long parentId) {
        List<Menu> list = menuMapper.selectList(new QueryWrapper<Menu>().eq("parent_id", parentId));
        return list;
    }

    @Override
    public void addMenu(Menu menu) {
        menu.setCtime(new Date());
        menu.setUtime(new Date());
        menuMapper.insert(menu);
    }

    @Override
    public void updateMenu(Menu menu) {
        menu.setUtime(new Date());
        menuMapper.updateById(menu);
    }

    @Override
    public boolean deleteMenu(Menu menu) {
        List<Menu> nextMenus = getByParentId(menu.getId());
        if(nextMenus ==null || nextMenus.size()==0){
            menuMapper.deleteById(menu.getId());
            return true;
        }else {
            return false;
        }
    }
}
