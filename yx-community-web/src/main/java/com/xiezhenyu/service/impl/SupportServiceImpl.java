package com.xiezhenyu.service.impl;

import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.service.ISupportService;
import com.xiezhenyu.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Tim
 * @date 2020/12/11
 */
@Service
public class SupportServiceImpl implements ISupportService {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public CommonResult supportContent(Long content_id, Long user_id) {
        boolean hasKey = false;
        if(redisUtil.hasKey("support_content_user_like_set_" + content_id)){
            hasKey = redisUtil.sHasKey("support_content_user_like_set_" + content_id, user_id);
        }
        if(hasKey){
            redisUtil.setRemove("support_content_user_like_set_" + content_id, user_id);
            redisUtil.del("support_content_user_like_"+content_id+"_"+user_id);
            redisUtil.decr("support_content_" + content_id + "_count", 1);
            Integer count = (Integer)redisUtil.get("support_content_" + content_id + "_count");
            if(count<1) {
                redisUtil.setRemove("support_content_set",content_id);
                redisUtil.del("support_content_"+content_id+"_count");
            }
            return CommonResult.successCommonResult(0,"取消点赞");
        }else{
            redisUtil.sSet("support_content_set",content_id);
            redisUtil.sSet("support_content_user_like_set_"+content_id,user_id);
            redisUtil.hset("support_content_user_like_"+content_id+"_"+user_id,"time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            redisUtil.hset("support_content_user_like_"+content_id+"_"+user_id,"state",1);
            if (redisUtil.hasKey("support_content_"+content_id+"_count")){
                redisUtil.incr("support_content_"+content_id+"_count",1);
            }else {
                redisUtil.set("support_content_"+content_id+"_count",1);
            }
        }
        return CommonResult.successCommonResult(1,"成功点赞");
    }

    @Override
    public CommonResult getSupportContentCount(Long content_id) {
        if(redisUtil.sHasKey("support_content_set",content_id)){
            Integer count = (Integer)redisUtil.get("support_content_" + content_id + "_count");
            return CommonResult.successCommonResult(count,"查询成功");
        }else {
            return CommonResult.successCommonResult(0,"查询成功");
        }
    }

    @Override
    public CommonResult supportReply(Long reply_id, Long user_id) {
        boolean hasKey = false;
        if(redisUtil.hasKey("support_reply_user_like_set_" + reply_id)){
            hasKey = redisUtil.sHasKey("support_reply_user_like_set_" + reply_id, user_id);
        }
        if(hasKey){
            redisUtil.setRemove("support_reply_user_like_set_" + reply_id, user_id);
            redisUtil.del("support_reply_user_like_"+reply_id+"_"+user_id);
            redisUtil.decr("support_reply_" + reply_id + "_count", 1);
            Integer count = (Integer)redisUtil.get("support_reply_" + reply_id + "_count");
            if(count<1) {
                redisUtil.setRemove("support_reply_set",reply_id);
                redisUtil.del("support_reply_"+reply_id+"_count");
            }
            return CommonResult.successCommonResult(0,"取消点赞");
        }else{
            redisUtil.sSet("support_reply_set",reply_id);
            redisUtil.sSet("support_reply_user_like_set_"+reply_id,user_id);
            redisUtil.hset("support_reply_user_like_"+reply_id+"_"+user_id,"time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            redisUtil.hset("support_reply_user_like_"+reply_id+"_"+user_id,"state",1);
            if (redisUtil.hasKey("support_reply_"+reply_id+"_count")){
                redisUtil.incr("support_reply_"+reply_id+"_count",1);
            }else {
                redisUtil.set("support_reply_"+reply_id+"_count",1);
            }
        }
        return CommonResult.successCommonResult(1,"成功点赞");
    }

    @Override
    public CommonResult getSupportReplyCount(Long reply_id) {
        if(redisUtil.sHasKey("support_reply_set",reply_id)){
            Integer count = (Integer)redisUtil.get("support_reply_" + reply_id + "_count");
            return CommonResult.successCommonResult(count,"查询成功");
        }else {
            return CommonResult.successCommonResult(0,"查询成功");
        }
    }
}
