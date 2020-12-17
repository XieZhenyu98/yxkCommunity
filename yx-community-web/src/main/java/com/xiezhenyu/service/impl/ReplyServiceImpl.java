package com.xiezhenyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.entity.ReplyVo;
import com.xiezhenyu.mapper.ReplyMapper;
import com.xiezhenyu.model.ReplyDo;
import com.xiezhenyu.service.IReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Tim
 */
@Service
public class ReplyServiceImpl implements IReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public boolean add(ReplyDo replyDo) {
        replyDo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        int num = replyMapper.insert(replyDo);
        return num>0;
    }

    @Override
    public boolean deleteById(Long id) {
        int num = replyMapper.deleteById(id);
        return num>0;
    }

    @Override
    public ReplyDo update(ReplyDo replyDo) {
        int num = replyMapper.updateById(replyDo);
        return replyMapper.selectById(replyDo.getId());
    }

    @Override
    public ReplyDo selectById(Long id) {
        return replyMapper.selectById(id);
    }

    @Override
    public Page<ReplyVo> selectList(Integer limit, Integer offset, Long contentId) {
        ArrayList<ReplyVo> list = replyMapper.getReplyVoList(contentId,limit,offset);
        Page<ReplyVo> replyVoPage = new Page<>();
        replyVoPage.setRecords(list).setSize(offset);
        return replyVoPage;
    }

    @Override
    public Page<ReplyVo> selectReReplyList(Long contentId, Long fatherReplyId, Integer limit, Integer offset) {
        ArrayList<ReplyVo> reReplyVoList = replyMapper.getReReplyVoList(contentId, fatherReplyId, limit, offset);
        Page<ReplyVo> replyVoPage = new Page<>();
        replyVoPage.setRecords(reReplyVoList);
        return replyVoPage;
    }

    @Override
    public List<ReplyDo> selectListByQuoteId(Long quoteId) {
        List<ReplyDo> replyList = replyMapper.selectList(new QueryWrapper<ReplyDo>().eq("quote_id", quoteId));
        return replyList;
    }

    @Override
    public List<ReplyDo> selectListByUserId(Long userId) {
        List<ReplyDo> replyList = replyMapper.selectList(new QueryWrapper<ReplyDo>().eq("user_id", userId));
        return replyList;
    }

    @Override
    public Integer selectCountByContent(Long contentId) {
        Integer num = replyMapper.selectCount(new QueryWrapper<ReplyDo>().eq("content_id", contentId));
        return num;
    }

    @Override
    public Integer selectCountByUser(Long userId) {
        Integer num = replyMapper.selectCount(new QueryWrapper<ReplyDo>().eq("user_id", userId));
        return num;
    }
}
