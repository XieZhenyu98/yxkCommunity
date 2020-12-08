package com.xiezhenyu.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tim
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("reply")
public class ReplyDo {

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @TableField("content_id")
    private Long contentId;

    @TableField("quote_id")
    private Long quoteId;

    @TableField("content")
    private String content;

    @TableField("time")
    private String time;

    @TableField("user_id")
    private Long userId;

}
