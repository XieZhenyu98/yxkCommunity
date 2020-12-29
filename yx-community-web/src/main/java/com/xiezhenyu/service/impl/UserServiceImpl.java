package com.xiezhenyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.entity.UserVo;
import com.xiezhenyu.mapper.UserMapper;
import com.xiezhenyu.model.UserDo;
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
    private RedisUtil redisUtil;
    private final static String REDIS_DB_USER_KEY = "user";

    @Override
    public boolean register(UserDo userDo) {
        userDo.setLastTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        userDo.setJoiningTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        Integer num = userMapper.insert(userDo);
        return num > 0;
    }

    @Override
    public CommonResult login(UserDo userDo) {
        CommonResult commonResult = null;
        ArrayList<Object> list = new ArrayList<>();
        String token = null;
        UserDo redisUser = null;
        if(!redisUtil.hHasKey(REDIS_DB_USER_KEY,userDo.getEmail())){
            redisUser = userMapper.selectOne(new QueryWrapper<UserDo>().eq("email",userDo.getEmail()).eq("password",userDo.getPassword()));
            if(redisUser == null){
                redisUtil.hset(REDIS_DB_USER_KEY,userDo.getEmail(),null,300);
                return CommonResult.errorCommonResult("账号或密码错误");
            }else {
                redisUtil.hset(REDIS_DB_USER_KEY,redisUser.getEmail(),redisUser,-1);
            }
        }
        if(redisUtil.hget(REDIS_DB_USER_KEY,userDo.getEmail())==null) {
            return CommonResult.errorCommonResult("账号或密码错误");
        }
        redisUser = (UserDo) redisUtil.hget(REDIS_DB_USER_KEY,userDo.getEmail());
        UserDo lastUser = redisUser.setLastTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        redisUtil.hset(REDIS_DB_USER_KEY,redisUser.getEmail(),lastUser,-1);
        userMapper.updateById(lastUser);
        Map<String,String> payload = new HashMap<>();
        payload.put("id",redisUser.getId().toString());
        payload.put("email",redisUser.getEmail());
        payload.put("username",redisUser.getUsername());
        payload.put("password",redisUser.getPassword());
        token = JwtUtils.getToken(payload);
        list.add(token);
        list.add(lastUser.toUserVo());
        commonResult = CommonResult.successCommonResult(list,"登录成功");
        return commonResult;
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
        return userMapper.selectById(userDo.getId());
    }

    @Override
    public boolean updatePassword(UserDo userDo) {
        UserDo redisUser = (UserDo)redisUtil.hget(REDIS_DB_USER_KEY, userDo.getEmail());
        if(!redisUser.getPassword().equals(userDo.getPassword())){
            return false;
        }
        redisUser.setPassword(userDo.getUsername());
        redisUtil.hset(REDIS_DB_USER_KEY,userDo.getEmail(),redisUser);
        userMapper.updateById(redisUser);
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
}
