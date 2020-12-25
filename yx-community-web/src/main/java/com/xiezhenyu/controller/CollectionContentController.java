package com.xiezhenyu.controller;

import com.xiezhenyu.model.CollectionContentDo;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.service.ICollectionContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tim
 * @date 2020/12/25
 */
@Api(tags = "收藏")
@RestController
@RequestMapping("/collection")
public class CollectionContentController {

    @Autowired
    private ICollectionContentService collectionContentService;

    @ApiOperation(value = "收藏帖子", notes = "收藏帖子")
    @PostMapping("/do")
    public CommonResult collectionContent(@ApiParam(value = "收藏实体类") @RequestBody CollectionContentDo collectionContentDo) {
        return collectionContentService.collectionContent(collectionContentDo);
    }

    @ApiOperation(value = "查询收藏总数", notes = "查询某帖子的收藏总数")
    @GetMapping("/count/{content_id}")
    public CommonResult collectionContentCount(@ApiParam(value = "帖子ID") @PathVariable("content_id")Long contentId){
        return collectionContentService.collectionContentCount(contentId);
    }

}
