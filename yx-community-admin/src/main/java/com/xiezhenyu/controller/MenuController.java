package com.xiezhenyu.controller;

import com.auth0.jwt.interfaces.Claim;
import com.xiezhenyu.model.admin.Menu;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.service.MenuService;
import com.xiezhenyu.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Tim
 * @date 2021/5/26
 */
@RequestMapping("/admin/menu")
@RestController
@Api("后台菜单")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation("查询所有菜单列表")
    @GetMapping("/all")
    public CommonResult getAllMenu() {
        List<Menu> allMenu = menuService.getAllMenu();
        return CommonResult.successCommonResult(allMenu,"查询成功");
    }

    @ApiOperation("通过权限id获取菜单列表")
    @GetMapping("/getByRoleId")
    public CommonResult getMenuByRoleId(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Long roleId = Long.parseLong(JwtUtils.getTokenInfo(token).getClaim("roleId").asString());
        List<Menu> menus = menuService.getMenuByRoleId(roleId);
        return CommonResult.successCommonResult(menus, "查询成功");
    }

}
