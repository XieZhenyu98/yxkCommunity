package com.xiezhenyu.service;

import com.xiezhenyu.model.SonModuleDo;

import java.util.ArrayList;


/**
 * @author Tim
 */
public interface ISonModuleService {

    /**
     * 添加子版块
     * @param sonModuleDo
     * @return
     */
    public boolean insert(SonModuleDo sonModuleDo);

    /**
     * 查询所有子版块
     * @param limit
     * @param offset
     * @return
     */
    public ArrayList<SonModuleDo> selectList(Integer limit,Integer offset);

    /**
     * 修改子版块信息
     * @param sonModuleDo
     * @return
     */
    public boolean update(SonModuleDo sonModuleDo);

    /**
     * 通过ID查询子版块信息
     * @param id
     * @return
     */
    public SonModuleDo selectById(Long id);

    /**
     * 通过ID删除子版块
     * @param id
     * @return
     */
    public boolean deleteById(Long id);

}
