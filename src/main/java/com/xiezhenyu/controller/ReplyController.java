package com.xiezhenyu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.common.response.CommonResult;
import com.xiezhenyu.entity.ReplyVo;
import com.xiezhenyu.model.ReplyDo;
import com.xiezhenyu.service.IReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tim
 * @date 2020/12/8
 */
@RestController
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private IReplyService replyService;

    @PostMapping("/add")
    public CommonResult add(@RequestBody ReplyDo replyDo){
        return CommonResult.successCommonResult(replyService.add(replyDo),"添加成功");
    }

    @DeleteMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id") Long id){
        boolean result = replyService.deleteById(id);
        if(result){
            return CommonResult.successCommonResult("删除成功");
        }else {
            return CommonResult.errorCommonResult("删除失败，请重试！");
        }
    }

    @PutMapping("/update")
    public CommonResult update(@RequestBody ReplyDo replyDo){
        ReplyDo reply = replyService.update(replyDo);
        return CommonResult.successCommonResult(reply,"添加成功");
    }

    @GetMapping("/{id}")
    public CommonResult selectById(@PathVariable("id") Long id){
        ReplyDo reply = replyService.selectById(id);
        return CommonResult.successCommonResult(reply,"查询成功");
    }

    @GetMapping("/list/{id}/{limit}/{offset}")
    public CommonResult selectList(@PathVariable("id") Long contentId,@PathVariable("limit") Integer limit,@PathVariable("offset") Integer offset){
        Page<ReplyVo> replyPage = replyService.selectList(0, 10, contentId);
        return CommonResult.successCommonResult(replyPage,"查询成功");
    }

    @GetMapping("/list/re/{id}/{fatherReplyId}/{limit}/{offset}")
    public CommonResult selectReReplyList(@PathVariable("id") Long contentId,@PathVariable("fatherReplyId") Long fatherReplyId,@PathVariable("limit") Integer limit,@PathVariable("offset") Integer offset){
        Page<ReplyVo> replyVoPage = replyService.selectReReplyList(contentId, fatherReplyId, limit, offset);
        return CommonResult.successCommonResult(replyVoPage,"查询成功");
    }

    @GetMapping("/select/quote/{id}")
    public CommonResult selectListByQuoteId(@PathVariable("id") Long quoteId){
        List<ReplyDo> replyList =  replyService.selectListByQuoteId(quoteId);
        return CommonResult.successCommonResult(replyList,"查询成功");
    }

    @GetMapping("/select/user/{id}")
    public CommonResult selectListByUserId(@PathVariable("id") Long userId){
        List<ReplyDo> replyList = replyService.selectListByUserId(userId);
        return CommonResult.successCommonResult(replyList,"查询成功");
    }
}
