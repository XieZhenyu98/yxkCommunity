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
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author Tim
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("content")
@ToString
@Accessors(chain = true)
public class ContentDo {

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id",type = IdType.ID_WORKER)
    private Long id;

    @TableField("module_id")
    private Long moduleId;

    @TableField("title")
    private String title;

    @TableField("content")
    private String content;

    @TableField("time")
    private String time;

    @TableField("user_id")
    private Long userId;

    @TableField("times")
    private Long times;

    @TableField("topping")
    private byte topping;

    @TableField("marvellous")
    private byte marvellous;

    @TableField("adopt_reply_id")
    private Long adoptReplyId;

    @TableField("money")
    private Integer money;

}