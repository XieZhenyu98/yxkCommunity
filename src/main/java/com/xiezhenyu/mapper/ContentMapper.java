package com.xiezhenyu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiezhenyu.entity.ContentVo;
import com.xiezhenyu.model.ContentDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
     * @param contentVo
     * @return
     */
    public ArrayList<ContentVo> selectListVo(Integer limit, Integer offset,@Param("contentVo") ContentVo contentVo);

    /**
     * 通过帖子ID获取帖子内容
     * @param contentVo
     * @return
     */
    public ContentVo selectContentById(@Param("contentVo") ContentVo contentVo);

}
