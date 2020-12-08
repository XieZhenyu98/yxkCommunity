package com.xiezhenyu.entity;

import com.xiezhenyu.model.SonModuleDo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;

/**
 * @author Tim
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class FatherModuleVo {

    private Long id;

    private String moduleName;

    private Integer sort;

    private ArrayList<SonModuleDo> sonModuleList;
}
