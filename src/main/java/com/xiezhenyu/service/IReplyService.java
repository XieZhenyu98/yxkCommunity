package com.xiezhenyu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.model.ReplyDo;

import java.util.List;

/**
 * @author Tim
 */
public interface IReplyService {

    /**
     * 增加回复
     * @param replyDo
     * @return
     */
    public boolean add(ReplyDo replyDo);

    /**
     * 根据ID删除回复
     * @param id
     * @return
     */
    public boolean deleteById(Long id);

    /**
     * 修改回复
     * @param replyDo
     * @return
     */
    public ReplyDo update(ReplyDo replyDo);

    /**
     * 根据ID查找回复
     * @param id
     * @return
     */
    public ReplyDo selectById(Long id);

    /**
     * 通过帖子Id分页查询回复
     * @param limit
     * @param offset
     * @param contentId
     * @return
     */
    public Page<ReplyDo> selectList(Integer limit, Integer offset, Long contentId);

    /**
     * 通过引用回复的ID查找所有该回复的内容
     * @param quoteId
     * @return
     */
    public List<ReplyDo> selectListByQuoteId(Long quoteId);

    /**
     * 通过用户ID查找该用户的所有回复
     * @param userId
     * @return
     */
    public List<ReplyDo> selectListByUserId(Long userId);

    /**
     * 通过帖子ID查询一共回复数
     * @param contentId
     * @return
     */
    public Integer selectCountByContent(Long contentId);

    /**
     * 通过用户ID查询一共回复数
     * @param userId
     * @return
     */
    public Integer selectCountByUser(Long userId);
}