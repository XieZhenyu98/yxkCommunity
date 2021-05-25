package com.xiezhenyu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.model.SearchKeywordsDo;

import java.util.List;

/**
 * @author Tim
 */
public interface ISearchKeywordsService {

    /**
     * 添加搜索记录
     * @param searchKeywordsDo
     * @return
     */
    boolean addKeywords(SearchKeywordsDo searchKeywordsDo);

    /**
     * 通过搜索数量获取搜索记录
     * @param pageSize
     * @return
     */
    Page<SearchKeywordsDo> getSearchKeywordsByCount(Integer pageSize);

    /**
     * 通过关键字获取搜索记录
     * @param keywords
     * @param pageSize
     * @return
     */
    Page<SearchKeywordsDo> getSearchKeywordsByKeywords(String keywords, Integer pageSize);
}
