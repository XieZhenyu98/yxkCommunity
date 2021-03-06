package com.xiezhenyu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.entity.SonModuleVo;
import com.xiezhenyu.model.SonModuleDo;
import com.xiezhenyu.query.SonModuleQuery;

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
    public ArrayList<SonModuleVo> selectList(Integer limit, Integer offset);

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
    public SonModuleVo selectById(Long id);

    /**
     * 通过ID删除子版块
     * @param id
     * @return
     */
    public boolean deleteById(Long id);

    /**
     * 通过父板块ID查询所有的子版块信息
     * @param id
     * @return
     */
    public ArrayList<SonModuleVo> selectListByFatherId(Long id);

    /**
     * 子版块分页
     * @param sonModuleQuery
     * @return
     */
    Page<SonModuleDo> getPage(SonModuleQuery sonModuleQuery);

    /**
     * 删除子版块
     * @param sonModuleDo
     * @return
     */
    boolean deleteSonModule(SonModuleDo sonModuleDo);
}
