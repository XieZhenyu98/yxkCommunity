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
import java.util.List;

/**
 * @author Tim
 * @date 2021/5/26
 */
@TableName("yxk_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField("parent_id")
    private Long parentId;

    @TableField("title")
    private String title;

    @TableField("level")
    private Integer level;

    @TableField("sort")
    private Integer sort;

    @TableField("icon")
    private String icon;

    @TableField("hidden")
    private Integer hidden;

    @TableField("ctime")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ctime;

    @TableField("utime")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date utime;

    @TableField("notes")
    private String notes;

    @TableField(exist = false)
    private List<Menu> nextMenus;

}
