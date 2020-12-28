package com.xiezhenyu.controller;

import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.model.SupportContentDo;
import com.xiezhenyu.model.SupportReplyDo;
import com.xiezhenyu.service.ISupportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tim
 * @date 2020/12/11
 */
@Api(tags = "点赞")
@RestController
@RequestMapping("/support")
public class SupportController {

    @Autowired
    private ISupportService supportService;

    @ApiOperation(value = "点赞帖子",notes = "点赞帖子")
    @PutMapping("/content")
    public CommonResult supportContent(@ApiParam(value = "") @RequestBody SupportContentDo supportContentDo){
        return supportService.supportContent(supportContentDo.getContentId(), supportContentDo.getUserId());
    }

    @ApiOperation(value = "获得帖子总点赞数",notes = "通过帖子ID获得该帖子的点赞总数")
    @GetMapping("/content/count/{id}")
    public CommonResult getSupportContentCount(@ApiParam(value = "") @PathVariable("id") Long content_id){
        return supportService.getSupportContentCount(content_id);
    }

    @ApiOperation(value = "点赞回复",notes = "点赞回复")
    @PutMapping("/reply")
    public CommonResult supportReply(@ApiParam(value = "") @RequestBody SupportReplyDo supportReplyDo){
        return supportService.supportReply(supportReplyDo.getReplyId(), supportReplyDo.getUserId());
    }

    @ApiOperation(value = "获得回复的点赞总数",notes = "通过回复的ID获取该回复的总点赞数")
    @GetMapping("/reply/count/{id}")
    public CommonResult getSupportReplyCount(@ApiParam(value = "") @PathVariable("id") Long reply_id){
        return supportService.getSupportReplyCount(reply_id);
    }
}
