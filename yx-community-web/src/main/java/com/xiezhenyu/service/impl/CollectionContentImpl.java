package com.xiezhenyu.service.impl;

import com.xiezhenyu.model.CollectionContentDo;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.service.ICollectionContentService;
import com.xiezhenyu.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Tim
 * @date 2020/12/25
 */
@Service
public class CollectionContentImpl implements ICollectionContentService {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public CommonResult collectionContent(CollectionContentDo collectionContentDo) {
        if (redisUtil.sHasKey("collection_user_"+collectionContentDo.getUser_id(),collectionContentDo.getContent_id())){
            redisUtil.setRemove("collection_user_"+collectionContentDo.getUser_id(),collectionContentDo.getContent_id());
            redisUtil.setRemove("collection_content",collectionContentDo.getContent_id());
            redisUtil.decr("collection_content_count_"+collectionContentDo.getContent_id(),1);
            Integer count = (Integer)redisUtil.get("collection_content_count_"+collectionContentDo.getContent_id());
            if(count<1) {
                redisUtil.del("collection_content_count_"+collectionContentDo.getContent_id());
            }
            return CommonResult.successCommonResult(0,"取消收藏");
        }else {
            if(redisUtil.sHasKey("collection_content",collectionContentDo.getContent_id())){
                redisUtil.incr("collection_content_count_"+collectionContentDo.getContent_id(),1);
            }else {
                redisUtil.sSet("collection_content",collectionContentDo.getContent_id());
                redisUtil.set("collection_content_count_"+collectionContentDo.getContent_id(),1);
            }
            redisUtil.sSet("collection_user_"+collectionContentDo.getUser_id(),collectionContentDo.getContent_id());
        }
        return CommonResult.successCommonResult(1,"收藏成功");
    }

    @Override
    public CommonResult collectionContentCount(Long content_id) {
        if(redisUtil.hasKey("collection_content_count_"+content_id)){
            Integer count = (Integer)redisUtil.get("collection_content_count_"+content_id);
            return CommonResult.successCommonResult(count,"查询成功");
        }else {
            return CommonResult.successCommonResult(0,"查询成功");
        }
    }

    @Override
    public Object[] getUserCollectionContentId(Long user_id) {
        if(redisUtil.hasKey("collection_user_"+user_id)){
            Set<Object> set = redisUtil.sGet("collection_user_" + user_id);
            Object[] array = set.toArray();
            return array;
        }
        return null;
    }

}
