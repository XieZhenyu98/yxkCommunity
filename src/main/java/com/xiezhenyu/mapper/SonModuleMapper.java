package com.xiezhenyu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiezhenyu.model.SonModuleDo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author Tim
 */
@Repository
@Mapper
public interface SonModuleMapper extends BaseMapper<SonModuleDo> {

    /**
     * 添加子板块
     * @param sonModuleDo
     * @return
     */
    @Override
    public int insert(SonModuleDo sonModuleDo);

    /**
     * 查询所有子版块
     * @param limit
     * @param offset
     * @return
     */
    public ArrayList<SonModuleDo> selectList(Integer limit,Integer offset);

    /**
     * 修改子板块信息
     * @param sonModuleDo
     * @return
     */
    public int update(SonModuleDo sonModuleDo);

    /**
     * 通过ID查询子版块信息
     * @param id
     * @return
     */
    public SonModuleDo selectById(Long id);

}
