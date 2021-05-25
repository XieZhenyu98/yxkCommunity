package com.xiezhenyu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.model.SearchKeywordsDo;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.service.ISearchKeywordsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tim
 * @date 2021/5/25
 */
@Api(tags = "查询记录")
@RequestMapping("/searchKeywords")
@RestController
public class SearchKeywordsController {
    @Autowired
    private ISearchKeywordsService searchKeywordsService;

    @ApiOperation(value = "添加搜索记录")
    @PostMapping("/addKeywords")
    public CommonResult addKeywords(@ApiParam(value = "关键字") @RequestBody SearchKeywordsDo searchKeywordsDo) {
        searchKeywordsService.addKeywords(searchKeywordsDo);
        return CommonResult.successCommonResult("插入成功");
    }

    @ApiOperation(value = "通过查询次数排序获取搜索记录")
    @GetMapping("/getSearchKeywordsByCount")
    public CommonResult getSearchKeywordsByCount(@ApiParam(value = "每页大小") Integer pageSize) {
        Page<SearchKeywordsDo> searchKeywordsByCount = searchKeywordsService.getSearchKeywordsByCount(pageSize);
        return CommonResult.successCommonResult(searchKeywordsByCount,"查询成功");
    }

    @ApiOperation(value = "通过关键字查询搜索记录列表")
    @GetMapping("/getSearchKeywordsByKeywords")
    public CommonResult getSearchKeywordsByKeywords(@ApiParam(value = "关键字") String keywords,
                                                    @ApiParam(value = "每页大小") Integer pageSize) {
        Page<SearchKeywordsDo> searchKeywordsByKeywords = searchKeywordsService.getSearchKeywordsByKeywords(keywords, pageSize);
        return CommonResult.successCommonResult(searchKeywordsByKeywords, "查询成功");
    }
}
