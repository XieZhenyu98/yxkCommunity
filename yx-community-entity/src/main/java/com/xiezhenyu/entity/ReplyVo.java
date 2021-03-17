package com.xiezhenyu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiezhenyu.model.ContentDo;
import com.xiezhenyu.model.ReplyDo;
import com.xiezhenyu.model.UserDo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Tim
 * @date 2020/12/12
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("son_module")
@ToString
public class ReplyVo {

    private Long id;

    private Long fatherReplyId;

    private Long contentId;

    private String content;

    private String time;

    private UserVo userVo;

    private ReplyVo replyVo;

}
