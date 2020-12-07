package com.xiezhenyu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.entity.FatherModuleVo;
import com.xiezhenyu.model.FatherModuleDo;

import java.util.ArrayList;

/**
 * @author Tim
 */
public interface IFatherModuleService {

    /**
     * 添加父板块
     * @param fatherModuleDo
     * @return
     */
    public FatherModuleDo add(FatherModuleDo fatherModuleDo);

    /**
     * 通过ID删除父板块
     * @param id
     * @return
     */
    public boolean deleteById(Long id);

    /**
     * 修改父板块信息
     * @param fatherModuleDo
     * @return
     */
    public FatherModuleDo update(FatherModuleDo fatherModuleDo);

    /**
     * 分页查询父板块信息
     * @param limit
     * @param offset
     * @return
     */
    public Page<FatherModuleVo> selectList(Integer limit, Integer offset);

    /**
     * 通过ID查询父板块
     * @param id
     * @return
     */
    public FatherModuleDo selectById(Long id);

}
