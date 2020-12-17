package com.xiezhenyu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.xiezhenyu.model.FatherModuleDo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Tim
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("son_module")
@ToString
public class SonModuleVo {

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField("module_name")
    private String moduleName;

    @TableField("info")
    private String info;

    @TableField("user_id")
    private Long userId;

    @TableField("sort")
    private Integer sort;

    @TableField("father_module_id")
    private FatherModuleDo fatherModule;

}
