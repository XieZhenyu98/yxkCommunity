package com.xiezhenyu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiezhenyu.model.ReplyDo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Tim
 */
@Repository
@Mapper
public interface ReplyMapper extends BaseMapper<ReplyDo> {
}
