package com.xiezhenyu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiezhenyu.model.admin.UserManage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户登录mapper
 * @author Tim
 */
@Mapper
@Repository
public interface LoginMapper extends BaseMapper<UserManage> {
}
