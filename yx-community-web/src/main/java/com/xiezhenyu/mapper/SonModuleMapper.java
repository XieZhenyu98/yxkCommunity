package com.xiezhenyu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiezhenyu.entity.SonModuleVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author Tim
 */
@Repository
@Mapper
public interface SonModuleMapper extends BaseMapper<SonModuleVo> {

    /**
     * 添加子板块
     * @param sonModuleVo
     * @return
     */
    @Override
    public int insert(SonModuleVo sonModuleVo);

    /**
     * 查询所有子版块
     * @param limit
     * @param offset
     * @return
     */
    public ArrayList<SonModuleVo> selectListPage(Integer limit, Integer offset);

    /**
     * 修改子板块信息
     * @param sonModuleVo
     * @return
     */
    public int update(SonModuleVo sonModuleVo);

    /**
     * 通过ID查询子版块信息
     * @param id
     * @return
     */
    public SonModuleVo selectById(Long id);

}
