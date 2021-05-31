package com.xiezhenyu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.model.admin.Role;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tim
 * @date 2021/5/27
 */
@Api("权限")
@RestController
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("通过id获取权限")
    @GetMapping("/getById")
    public CommonResult getRoleByRoleId(@ApiParam("权限id") Long roleId) {
        Role role = roleService.getRoleById(roleId);
        return CommonResult.successCommonResult(role,"查询成功");
    }

    @ApiOperation("分页获取权限")
    @GetMapping("/page")
    public CommonResult getRolePage(@ApiParam("第几页") Integer pageNo,
                                    @ApiParam("大小") Integer pageSize,
                                    @ApiParam("权限名") String name) {
        Page<Role> rolePage = roleService.getRolePage(pageNo, pageSize, name);
        return CommonResult.successCommonResult(rolePage,"查询成功");
    }

    @ApiOperation("添加权限")
    @PostMapping("/add")
    public CommonResult addRole(@ApiParam("权限实体类") @RequestBody Role role) {
        roleService.addRole(role);
        return CommonResult.successCommonResult("添加成功");
    }

    @ApiOperation("修改权限")
    @PostMapping("/update")
    public CommonResult updateRole(@ApiParam("权限实体类") @RequestBody Role role) {
        roleService.updateRole(role);
        return CommonResult.successCommonResult("修改成功");
    }

    @ApiOperation("删除权限")
    @PostMapping("/delete")
    public CommonResult deleteRole(@ApiParam("权限实体类") @RequestBody Role role) {
        boolean res = roleService.deleteRole(role);
        if(res){
            return CommonResult.successCommonResult("删除成功");
        }else{
            return CommonResult.errorCommonResult("删除失败，请先删除或修改该权限下的用户！");
        }
    }

    @ApiOperation("获取所有权限")
    @GetMapping("/all")
    public CommonResult roleAll(){
        List<Role> roles = roleService.roleAll();
        return CommonResult.successCommonResult(roles,"查询成功");
    }
}
