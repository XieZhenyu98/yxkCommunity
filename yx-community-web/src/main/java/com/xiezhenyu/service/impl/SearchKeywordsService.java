package com.xiezhenyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.mapper.SearchKeywordsMapper;
import com.xiezhenyu.model.SearchKeywordsDo;
import com.xiezhenyu.service.ISearchKeywordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Tim
 * @date 2021/5/25
 */
@Service
public class SearchKeywordsService implements ISearchKeywordsService {

    @Autowired
    private SearchKeywordsMapper searchKeywordsMapper;

    @Override
    public boolean addKeywords(SearchKeywordsDo searchKeywordsDo) {
        SearchKeywordsDo res = searchKeywordsMapper.selectOne(new QueryWrapper<SearchKeywordsDo>().eq("keywords", searchKeywordsDo.getKeywords()));
        if(res==null) {
            searchKeywordsDo.setCount(1);
            searchKeywordsDo.setCtime(new Date());
            searchKeywordsDo.setLastSearchTime(new Date());
            searchKeywordsMapper.insert(searchKeywordsDo);
        }else {
            res.setLastSearchTime(new Date());
            res.setCount(res.getCount()+1);
            searchKeywordsMapper.updateById(res);
        }
        return true;
    }

    @Override
    public Page<SearchKeywordsDo> getSearchKeywordsByCount(Integer pageSize) {
        Page<SearchKeywordsDo> countPage = searchKeywordsMapper.selectPage(new Page<>(0, pageSize), new QueryWrapper<SearchKeywordsDo>().orderByDesc("count"));
        return countPage;
    }

    @Override
    public Page<SearchKeywordsDo> getSearchKeywordsByKeywords(String keywords, Integer pageSize) {
        Page<SearchKeywordsDo> searchKeywordsDoPage = searchKeywordsMapper.selectPage(new Page<>(0, pageSize), new QueryWrapper<SearchKeywordsDo>().like("keywords", keywords).orderByDesc("count"));
        return searchKeywordsDoPage;
    }
}
