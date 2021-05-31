package com.xiezhenyu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiezhenyu.model.admin.Icon;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Tim
 * @date 2021/5/30
 */
@Repository
@Mapper
public interface IconMapper extends BaseMapper<Icon> {
}
