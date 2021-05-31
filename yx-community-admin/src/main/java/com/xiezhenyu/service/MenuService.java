package com.xiezhenyu.service;

import com.xiezhenyu.model.admin.Menu;

import java.util.List;

/**
 * @author Tim
 */
public interface MenuService {

    /**
     * 获取所有的Menu
     * @return
     */
    List<Menu> getAllMenu();

    /**
     * 通过权限id获取菜单
     * @param roleId
     * @return
     */
    List<Menu> getMenuByRoleId(Long roleId);

    /**
     * 通过父菜单id获取菜单
     * @param parentId
     * @return
     */
    List<Menu> getByParentId(Long parentId);

    /**
     * 添加菜单
     * @param menu
     */
    void addMenu(Menu menu);

    /**
     * 修改菜单
     * @param menu
     */
    void updateMenu(Menu menu);

    /**
     * 删除菜单
     * @param menu
     * @return
     */
    boolean deleteMenu(Menu menu);

    /**
     * 通过id获取菜单
     * @param id
     * @return
     */
    Menu getById(Long id);

    /**
     * 获取所有菜单
     * @return
     */
    List<Menu> getAll();

}
