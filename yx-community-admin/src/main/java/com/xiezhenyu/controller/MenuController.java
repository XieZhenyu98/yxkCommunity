package com.xiezhenyu.controller;

import com.xiezhenyu.model.admin.Menu;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.service.MenuService;
import com.xiezhenyu.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("通过父菜单id获取菜单")
    @GetMapping("/byParentId")
    public CommonResult getByParentId(@ApiParam("父菜单id") Long parentId) {
        List<Menu> menus = menuService.getByParentId(parentId);
        return CommonResult.successCommonResult(menus,"查询成功");
    }

    @ApiOperation("通过权限id获取菜单列表")
    @GetMapping("/getByRoleId")
    public CommonResult getMenuByRoleId(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Long roleId = Long.parseLong(JwtUtils.getTokenInfo(token,"roleId"));
        List<Menu> menus = menuService.getMenuByRoleId(roleId);
        return CommonResult.successCommonResult(menus, "查询成功");
    }

    @ApiOperation("添加菜单")
    @PostMapping("/add")
    public CommonResult addMenu(@ApiParam("菜单实体类") @RequestBody Menu menu) {
        menuService.addMenu(menu);
        return CommonResult.successCommonResult("插入成功");
    }

    @ApiOperation("修改菜单")
    @PostMapping("/update")
    public CommonResult updateMenu(@ApiParam("菜单实体类") @RequestBody Menu menu) {
        menuService.updateMenu(menu);
        return CommonResult.successCommonResult("修改成功");
    }

    @ApiOperation("删除菜单")
    @PostMapping("/delete")
    public CommonResult deleteMenu(@ApiParam("菜单实体类") @RequestBody Menu menu) {
        boolean res = menuService.deleteMenu(menu);
        if(res){
            return CommonResult.successCommonResult("删除成功");
        }else{
            return CommonResult.errorCommonResult("删除失败，请先删除子菜单。");
        }
    }
}
