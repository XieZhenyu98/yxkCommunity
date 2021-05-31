package com.xiezhenyu.model.admin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author Tim
 * @date 2021/4/13
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_manage")
public class UserManage {

    /**
     * 管理员id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 管理员用户名
     */
    @TableField("name")
    private String name;

    /**
     * 管理员密码
     */
    @TableField("password")
    private String password;

    /**
     * 帐号创建时间
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;

    /**
     * 最后登录时间
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("last_login_time")
    private Date lastLoginTime;

    /**
     * 管理员权限等级
     */
    @TableField("role_id")
    private Long roleId;

    @TableField(exist = false)
    private Role role;
}
