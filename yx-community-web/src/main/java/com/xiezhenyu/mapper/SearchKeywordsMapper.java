package com.xiezhenyu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiezhenyu.model.SearchKeywordsDo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Tim
 */
@Repository
@Mapper
public interface SearchKeywordsMapper extends BaseMapper<SearchKeywordsDo> {
}
