package com.xiezhenyu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.entity.UserVo;
import com.xiezhenyu.model.UserDo;
import com.xiezhenyu.query.UserQuery;
import com.xiezhenyu.response.CommonResult;

import java.util.List;

/**
 * @author Tim
 */
public interface IUserService {
    /**
     * 添加用户
     * @param userDo
     * @return
     */
    public boolean register(UserDo userDo);

    /**
     * 用户登录
     * @param userDo
     * @return
     */
    public CommonResult login(UserDo userDo);

    /**
     * 通过id查找用户
     * @param id
     * @return
     */
    public UserDo getUserById(Long id);

    /**
     * 查看用户列表
     * @param limit  分页大小
     * @param offset 偏移值
     * @return
     */
    public List<UserDo> list(Integer limit,Integer offset);

    /**
     * 修改用户
     * @param userDo
     * @return
     */
    public UserDo updateUser(UserDo userDo);

    /**
     * 修改用户密码
     * @param userDo
     * @return
     */
    public boolean updatePassword(UserDo userDo);

    /**
     * 通过用户等级查询用户排行
     * @return
     */
    public Page<UserVo> userListByEx();

    /**
     * 获取用户列表
     * @param userQuery
     * @return
     */
    public Page<UserDo> userList(UserQuery userQuery);

    /**
     * 删除用户
     * @param userDo
     * @return
     */
    public boolean deleteUser(UserDo userDo);

    /**
     * 恢复用户
     * @param userDo
     * @return
     */
    public boolean recoveryUser(UserDo userDo);

    /**
     * 重置密码
     * @param userDo
     * @return
     */
    boolean resetPassword(UserDo userDo);
}
