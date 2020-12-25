package com.xiezhenyu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.entity.PersonalReplyVo;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.entity.ReplyVo;
import com.xiezhenyu.model.ReplyDo;
import com.xiezhenyu.service.IReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tim
 * @date 2020/12/8
 */
@Api(tags = "回帖")
@RestController
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private IReplyService replyService;

    @ApiOperation(value = "添加回帖",notes = "添加回帖")
    @PostMapping("/add")
    public CommonResult add(@ApiParam(value = "回帖实体类") @RequestBody ReplyDo replyDo){
        return CommonResult.successCommonResult(replyService.add(replyDo),"添加成功");
    }

    @ApiOperation(value = "删除回帖",notes = "通过回帖ID删除回帖")
    @DeleteMapping("/delete/{id}")
    public CommonResult delete(@ApiParam(value = "回帖ID") @PathVariable("id") Long id){
        boolean result = replyService.deleteById(id);
        if(result){
            return CommonResult.successCommonResult("删除成功");
        }else {
            return CommonResult.errorCommonResult("删除失败，请重试！");
        }
    }

    @ApiOperation(value = "修改回帖",notes = "修改回帖")
    @PutMapping("/update")
    public CommonResult update(@ApiParam(value = "回帖实体类") @RequestBody ReplyDo replyDo){
        ReplyDo reply = replyService.update(replyDo);
        return CommonResult.successCommonResult(reply,"修改成功");
    }

    @ApiOperation(value = "回帖ID查询回帖",notes = "通过回帖的ID查询回帖")
    @GetMapping("/{id}")
    public CommonResult selectById(@ApiParam(value = "回帖ID") @PathVariable("id") Long id){
        ReplyDo reply = replyService.selectById(id);
        return CommonResult.successCommonResult(reply,"查询成功");
    }

    @ApiOperation(value = "通过帖子ID查询回帖",notes = "通过帖子的ID分页查询该帖子下的回帖")
    @GetMapping("/list/{id}/{limit}/{offset}")
    public CommonResult selectList(@ApiParam(value = "帖子ID") @PathVariable("id") Long contentId,
                                   @ApiParam(value = "第几条") @PathVariable("limit") Integer limit,
                                   @ApiParam(value = "偏移量") @PathVariable("offset") Integer offset){
        Page<ReplyVo> replyPage = replyService.selectList(0, 10, contentId);
        return CommonResult.successCommonResult(replyPage,"查询成功");
    }

    @ApiOperation(value = "帖子ID和引用ID查询引用回复",notes = "通过帖子的ID和引用回帖的ID分页查询引用回复")
    @GetMapping("/list/re/{id}/{fatherReplyId}/{limit}/{offset}")
    public CommonResult selectReReplyList(@ApiParam(value = "帖子ID") @PathVariable("id") Long contentId,
                                          @ApiParam(value = "引用回帖ID") @PathVariable("fatherReplyId") Long fatherReplyId,
                                          @ApiParam(value = "第几条") @PathVariable("limit") Integer limit,
                                          @ApiParam(value = "偏移量") @PathVariable("offset") Integer offset){
        Page<ReplyVo> replyVoPage = replyService.selectReReplyList(contentId, fatherReplyId, limit, offset);
        return CommonResult.successCommonResult(replyVoPage,"查询成功");
    }

    @ApiOperation(value = "引用回复的ID查找所有该回复的内容",notes = "通过引用回复的ID查找所有该回复的内容")
    @GetMapping("/select/quote/{id}")
    public CommonResult selectListByQuoteId(@ApiParam(value = "引用回帖ID") @PathVariable("id") Long quoteId){
        List<ReplyDo> replyList =  replyService.selectListByQuoteId(quoteId);
        return CommonResult.successCommonResult(replyList,"查询成功");
    }

    @ApiOperation(value = "用户ID查询回帖",notes = "通过用户ID查询该用户所有的回帖")
    @GetMapping("/select/user/{id}/{limit}/{offset}")
    public CommonResult selectListByUserId(@ApiParam(value = "用户ID") @PathVariable("id") Long userId,
                                           @ApiParam(value = "第几页") @PathVariable("limit") Integer limit,
                                           @ApiParam(value = "偏移量") @PathVariable("offset") Integer offset){
        Page<ReplyDo> page = replyService.selectListByUserId(userId,limit,offset);
        return CommonResult.successCommonResult(page,"查询成功");
    }

    @ApiOperation(value = "用户主页回帖情况查询",notes = "查询用户主页回帖情况")
    @GetMapping("/select/personal/{userId}/{limit}/{offset}")
    public CommonResult getPersonalReplyVoByUserId(@ApiParam(value = "用户ID") @PathVariable("userId")Long userId,
                                                   @ApiParam(value = "第几条") @PathVariable("limit")Integer limit,
                                                   @ApiParam(value = "偏移量") @PathVariable("offset")Integer offset){
        ArrayList<PersonalReplyVo> list = replyService.getPersonalReplyVoByUserId(userId, limit, offset);
        return CommonResult.successCommonResult(list,"查询成功");
    }

}
