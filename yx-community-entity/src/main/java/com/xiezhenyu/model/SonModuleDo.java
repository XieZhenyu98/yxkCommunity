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
 * @date 2020/12/9
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("son_module")
public class SonModuleDo {

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
    private Long fatherModuleId;

    @TableField(exist = false)
    private UserDo userDo;

    @TableField(exist = false)
    private FatherModuleDo fatherModuleDo;


}
