package com.xiezhenyu.service;

import com.xiezhenyu.common.response.CommonResult;
import com.xiezhenyu.model.UserDo;
import org.springframework.stereotype.Service;

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
}
