package com.xiezhenyu.service;

import com.xiezhenyu.model.CollectionContentDo;
import com.xiezhenyu.response.CommonResult;

/**
 * @author Tim
 * @date 2020/12/25
 */
public interface ICollectionContentService {

    /**
     * 收藏帖子\取消收藏帖子
     * @param collectionContentDo
     * @return
     */
    public CommonResult collectionContent(CollectionContentDo collectionContentDo);

    /**
     * 查询帖子收藏总数
     * @param content_id
     * @return
     */
    public CommonResult collectionContentCount(Long content_id);

    /**
     * 查看用户收藏的所有帖子
     * @param user_id
     * @return
     */
    public Object[] getUserCollectionContentId(Long user_id);

}
