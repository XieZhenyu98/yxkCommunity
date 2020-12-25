package com.xiezhenyu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.entity.ContentVo;
import com.xiezhenyu.model.ContentDo;
import com.xiezhenyu.service.IContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tim
 */
@Api(tags = "帖子")
@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private IContentService contentService;

    @ApiOperation(value = "添加帖子")
    @PostMapping("/add")
    public CommonResult add(@ApiParam(value = "帖子实体类") @RequestBody ContentDo contentDo){
        ContentDo result = contentService.add(contentDo);
        return CommonResult.successCommonResult(result,"插入成功");
    }

    @ApiOperation(value = "获取帖子", notes = "通过帖子ID获取帖子")
    @GetMapping("/{id}")
    public CommonResult getContentById(@ApiParam(value = "帖子ID") @PathVariable("id") Long id){
        ContentVo content = contentService.getContentById(id);
        return CommonResult.successCommonResult(content,"查询成功");
    }

    @ApiOperation(value = "帖子列表", notes = "分页查询帖子列表")
    @GetMapping("/{limit}/{offset}")
    public CommonResult list(@ApiParam(value = "第几页") @PathVariable("limit") Integer limit,
                             @ApiParam(value = "偏移量") @PathVariable("offset") Integer offset){
        Page<ContentVo> page = new Page<>(limit,offset);
        ArrayList<ContentVo> contentVos = contentService.selectListVo(limit, offset);
        page.setRecords(contentVos);
        return CommonResult.successCommonResult(page,"查询成功");
    }

    @ApiOperation(value = "模板ID获取帖子列表",notes = "通过模板ID分页查询帖子列表")
    @GetMapping("/{limit}/{offset}/{module_id}")
    public CommonResult listByModuleId(@ApiParam(value = "第几页") @PathVariable("limit")Integer limit,
                                       @ApiParam(value = "偏移量") @PathVariable("offset")Integer offset,
                                       @ApiParam(value = "模块ID") @PathVariable("module_id")Long module_id){
        Page<ContentVo> page = contentService.listByModuleId(module_id, limit, offset);
        return CommonResult.successCommonResult(page,"查询成功");
    }

    @ApiOperation(value = "指定帖子列表", notes = "获取置顶帖子的列表")
    @GetMapping("/listOfTop")
    public CommonResult listOfTop(){
        List<ContentVo> list = contentService.listOfTop();
        return CommonResult.successCommonResult(list,"查询成功");
    }

    @ApiOperation(value = "修改帖子", notes = "通过接收帖子的内容覆盖原来的内容")
    @PutMapping("/update")
    public CommonResult updateContent(@ApiParam(value = "帖子实体类") @RequestBody ContentDo contentDo){
        ContentDo content = contentService.updateContent(contentDo);
        return CommonResult.successCommonResult(content,"修改成功");
    }

    @ApiOperation(value = "删除帖子", notes = "同过帖子ID删除帖子")
    @DeleteMapping("/delete/{id}")
    public CommonResult deleteContentById(@ApiParam(value = "帖子ID") @PathVariable("id")Long id){
        boolean result = contentService.deleteContentById(id);
        if(result){
            return CommonResult.successCommonResult("删除成功");
        }else{
            return CommonResult.errorCommonResult("删除失败");
        }
    }

    @ApiOperation(value = "用户ID获取帖子列表", notes = "通过用户的ID分页获取帖子列表")
    @GetMapping("/select/user/{id}/{limit}/{offset}")
    public CommonResult selectListByUserId(@ApiParam(value = "用户ID") @PathVariable("id") Long id,
                                           @ApiParam(value = "第几条记录开始") @PathVariable Integer limit,
                                           @ApiParam(value = "偏移量") @PathVariable Integer offset){
        Page<ContentDo> page = contentService.selectListByUserId(id, limit, offset);
        return CommonResult.successCommonResult(page,"查询成功");
    }

    @ApiOperation(value = "查询用户收藏的帖子", notes = "分页查询用户收藏的帖子")
    @GetMapping("/select/collection/{limit}/{offset}/{userId}")
    public CommonResult selectContentByCollectionContentIdList(@ApiParam(value = "第几条") @PathVariable("limit")Integer limit,
                                                               @ApiParam(value = "偏移量") @PathVariable("offset")Integer offset,
                                                               @ApiParam(value = "用户ID") @PathVariable("userId") Long userId){
        List<ContentDo> contentDos = contentService.selectContentByCollectionContentIdList(limit, offset, userId);
        return CommonResult.successCommonResult(contentDos,"查询成功");
    }

}
