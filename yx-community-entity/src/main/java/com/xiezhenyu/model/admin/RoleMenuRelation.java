package com.xiezhenyu.model.admin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Tim
 * @date 2021/5/26
 */
@Data
@TableName("yxk_role_menu_relation")
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenuRelation {

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField("role_id")
    private Long roleId;

    @TableField(exist = false)
    private Role role;

    @TableField("menu_id")
    private Long menuId;

    @TableField(exist = false)
    private Menu menu;

    @TableField("ctime")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ctime;

    @TableField("utime")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date utime;

}
