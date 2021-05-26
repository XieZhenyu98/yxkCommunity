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

}
