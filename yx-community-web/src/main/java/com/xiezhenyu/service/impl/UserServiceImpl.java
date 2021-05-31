package com.xiezhenyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.construct.CommonConfig;
import com.xiezhenyu.query.UserQuery;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.entity.UserVo;
import com.xiezhenyu.mapper.UserMapper;
import com.xiezhenyu.model.UserDo;
import com.xiezhenyu.service.ICommonConfigService;
import com.xiezhenyu.service.IUserService;
import com.xiezhenyu.utils.JwtUtils;
import com.xiezhenyu.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Tim
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ICommonConfigService commonConfigService;

    @Override
    public boolean register(UserDo userDo) {
        userDo.setLastTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        userDo.setJoiningTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        Integer num = userMapper.insert(userDo);
        return num > 0;
    }

    @Override
    public CommonResult login(UserDo userDo) {
        UserDo userDB = userMapper.selectOne(new QueryWrapper<UserDo>().eq("email", userDo.getEmail()));
        if(userDB==null||!userDB.getPassword().equals(userDo.getPassword())){
            return CommonResult.errorCommonResult("用户名或者密码错误！");
        }
        ArrayList<Object> list = new ArrayList<>();
        String token = null;
        UserDo lastUser = userDB.setLastTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        userMapper.updateById(lastUser);
        Map<String,String> payload = new HashMap<>();
        payload.put("id",userDB.getId().toString());
        payload.put("email",userDB.getEmail());
        payload.put("username",userDB.getUsername());
        payload.put("password",userDB.getPassword());
        token = JwtUtils.getToken(payload);
        list.add(token);
        list.add(lastUser.toUserVo());
        return CommonResult.successCommonResult(list,"登录成功");
    }

    @Override
    public UserDo getUserById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<UserDo> list(Integer limit, Integer offset) {
        return null;
    }

    @Override
    public UserDo updateUser(UserDo userDo) {
        userMapper.updateById(userDo);
        UserDo user = userMapper.selectById(userDo.getId());
        login(user);
        return user;
    }

    @Override
    public boolean updatePassword(UserDo userDo) {
        UserDo userDB = userMapper.selectOne(new QueryWrapper<UserDo>().eq("email",userDo.getEmail()));
        if(!userDB.getPassword().equals(userDo.getPassword())){
            return false;
        }
        userDB.setPassword(userDo.getUsername());
        userMapper.updateById(userDB);
        return true;
    }

    @Override
    public Page<UserVo> userListByEx() {
        Page<UserDo> experience = userMapper.selectPage(new Page<UserDo>(0, 9), new QueryWrapper<UserDo>().orderByDesc("experience"));
        List<UserVo> list = new ArrayList<UserVo>();
        Page<UserVo> pageVo = new Page<UserVo>();
        for(UserDo userDo : experience.getRecords()){
            list.add(userDo.toUserVo());
        }
        pageVo.setRecords(list).setSize(experience.getSize()).setCurrent(experience.getCurrent()).setTotal(experience.getTotal());
        return pageVo;
    }

    @Override
    public Page<UserDo> userList(UserQuery userQuery) {
        Page page = null;
        if(userQuery.getIsDelete()!=null){
            page = userMapper.selectPage(new Page(userQuery.getPageNo(), userQuery.getPageSize()), new QueryWrapper<UserDo>().like("username", userQuery.getUsername()).like("email", userQuery.getEmail()).eq("is_delete",userQuery.getIsDelete()));
        }else{
            page = userMapper.selectPage(new Page(userQuery.getPageNo(), userQuery.getPageSize()), new QueryWrapper<UserDo>().like("username", userQuery.getUsername()).like("email", userQuery.getEmail()));
        }
        return page;
    }

    @Override
    public boolean deleteUser(UserDo userDo) {
        userDo.setIsDelete(1);
        userMapper.updateById(userDo);
        return true;
    }

    @Override
    public boolean recoveryUser(UserDo userDo) {
        userDo.setIsDelete(0);
        userMapper.updateById(userDo);
        return true;
    }

    @Override
    public boolean resetPassword(UserDo userDo) {
        userDo.setPassword(commonConfigService.getByPrefixAndKey(CommonConfig.USER_PREFIX,CommonConfig.USER_DEFAULT_PASSWORD_KEY).getConfigValue());
        userMapper.updateById(userDo);
        return true;
    }

}
