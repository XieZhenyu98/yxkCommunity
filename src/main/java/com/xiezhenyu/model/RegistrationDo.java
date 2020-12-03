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
 * 签到实体类
 * @author Tim
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("registration")
public class RegistrationDo {

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id",type = IdType.ID_WORKER)
    private Long id;

    @TableField("date")
    private String date;

    @TableField("content")
    private String content;

    @TableField("user_id")
    private Long userId;

}