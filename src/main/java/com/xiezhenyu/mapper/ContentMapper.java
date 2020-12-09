package com.xiezhenyu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiezhenyu.entity.ContentVo;
import com.xiezhenyu.model.ContentDo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author Tim
 */
@Mapper
@Repository
public interface ContentMapper extends BaseMapper<ContentDo> {

    /**
     * 查询帖子内容列表
     * @param limit
     * @param offset
     * @return
     */
    public ArrayList<ContentVo> selectListVo(Integer limit, Integer offset);
}
