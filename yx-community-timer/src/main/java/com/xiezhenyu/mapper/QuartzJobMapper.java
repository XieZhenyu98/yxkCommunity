package com.xiezhenyu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiezhenyu.entity.QuartzJob;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Tim
 * @date 2021/03/24
 */
@Repository
@Mapper
public interface QuartzJobMapper extends BaseMapper<QuartzJob> {
}
