package com.xiezhenyu.controller;

import com.xiezhenyu.common.response.CommonResult;
import com.xiezhenyu.model.SupportContentDo;
import com.xiezhenyu.model.SupportReplyDo;
import com.xiezhenyu.service.ISupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tim
 * @date 2020/12/11
 */
@RestController
@RequestMapping("/support")
public class SupportController {

    @Autowired
    private ISupportService supportService;

    @PutMapping("/content")
    public CommonResult supportContent(@RequestBody SupportContentDo supportContentDo){
        return supportService.supportContent(supportContentDo.getContentId(), supportContentDo.getUserId());
    }

    @GetMapping("/content/count/{id}")
    public CommonResult getSupportContentCount(@PathVariable("id") Long content_id){
        return supportService.getSupportContentCount(content_id);
    }

    @PutMapping("/reply")
    public CommonResult supportReply(@RequestBody SupportReplyDo supportReplyDo){
        return supportService.supportContent(supportReplyDo.getReplyId(), supportReplyDo.getUserId());
    }

    @GetMapping("/reply/count/{id}")
    public CommonResult getSupportReplyCount(@PathVariable("id") Long reply_id){
        return supportService.getSupportContentCount(reply_id);
    }
}
