package com.xiezhenyu.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.xiezhenyu.entity.FatherModuleVo;
import com.xiezhenyu.entity.SonModuleVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @author Tim
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("father_module")
public class FatherModuleDo {

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField("module_name")
    private String moduleName;

    @TableField("sort")
    private Integer sort;

    public FatherModuleVo toVo(ArrayList<SonModuleVo> list){
        FatherModuleVo fatherModuleVo = new FatherModuleVo();
        fatherModuleVo.setId(this.getId())
                .setModuleName(this.getModuleName())
                .setSort(this.getSort())
                .setSonModuleList(list);
        return fatherModuleVo;
    }
}
