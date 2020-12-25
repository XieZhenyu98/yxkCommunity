package com.xiezhenyu.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.xiezhenyu.entity.ContentVo;
import com.xiezhenyu.entity.SonModuleVo;
import com.xiezhenyu.entity.UserVo;
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
    @TableId(value = "id",type = IdType.AUTO)
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
    private Byte topping;

    @TableField("marvellous")
    private Byte marvellous;

    @TableField("adopt_reply_id")
    private Long adoptReplyId;

    @TableField("money")
    private Integer money;

    public ContentVo toVo(SonModuleVo sonModuleVo, UserVo userVo, Integer replyNum){
        ContentVo contentVo = new ContentVo();
        contentVo.setId(this.id)
                .setTitle(this.title)
                .setContent(this.content)
                .setTime(this.time)
                .setTimes(this.times)
                .setTopping(this.topping)
                .setMarvellous(this.marvellous)
                .setAdoptReplyId(this.adoptReplyId)
                .setMoney(this.money)
                .setSonModule(sonModuleVo)
                .setUserVo(userVo)
                .setReplyNum(replyNum);
        return contentVo;
    }
}
