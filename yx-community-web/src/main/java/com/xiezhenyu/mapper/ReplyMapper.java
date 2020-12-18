package com.xiezhenyu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiezhenyu.entity.PersonalReplyVo;
import com.xiezhenyu.entity.ReplyVo;
import com.xiezhenyu.model.ReplyDo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author Tim
 */
@Repository
@Mapper
public interface ReplyMapper extends BaseMapper<ReplyDo> {

    /**
     * 获取回复列表
     * @param content_id
     * @param limit
     * @param offset
     * @return
     */
    public ArrayList<ReplyVo> getReplyVoList(Long content_id,Integer limit,Integer offset);

    /**
     * 获取回复回复的内容
     * @param content_id
     * @param father_reply_id
     * @param limit
     * @param offset
     * @return
     */
    public ArrayList<ReplyVo> getReReplyVoList(Long content_id,Long father_reply_id,Integer limit,Integer offset);

    /**
     * 通过用户Id查找用户中心需要的该用户回复列表
     * @param user_id
     * @param limit
     * @param offset
     * @return
     */
    public ArrayList<PersonalReplyVo> getPersonalReplyVoByUserId(Long user_id, Integer limit, Integer offset);
}
