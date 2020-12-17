package com.xiezhenyu.service;

import com.xiezhenyu.response.CommonResult;

/**
 * @author Tim
 */
public interface ISupportService {

    /**
     * 点赞帖子
     * @param content_id
     * @param user_id
     * @return
     */
    public CommonResult supportContent(Long content_id, Long user_id);

    /**
     * 获取帖子的总赞数
     * @param content_id
     * @return
     */
    public CommonResult getSupportContentCount(Long content_id);

    /**
     * 点赞回复
     * @param reply_id
     * @param user_id
     * @return
     */
    public CommonResult supportReply(Long reply_id, Long user_id);

    /**
     * 获取回复的总赞数
     * @param reply_id
     * @return
     */
    public CommonResult getSupportReplyCount(Long reply_id);
}
