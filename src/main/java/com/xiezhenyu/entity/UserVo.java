package com.xiezhenyu.entity;


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
@TableName("user")
@ToString
@Accessors(chain = true)
public class UserVo {
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id",type = IdType.ID_WORKER)
    private Long id;
    @TableField("email")
    private String email;
    @TableField("username")
    private String username;
    @TableField("sex")
    private Byte sex;
    @TableField("city")
    private String city;
    @TableField("personal_signature")
    private String personalSignature;
    @TableField("image")
    private String image;
    @TableField("activate_mailbox")
    private Byte activateMailbox;
    @TableField("diamonds")
    private Integer diamonds;
    @TableField("authentication")
    private String authentication;
    @TableField("joining_time")
    private String joiningTime;
    @TableField("last_time")
    private String lastTime;
    @TableField("experience")
    private Long experience;
}
