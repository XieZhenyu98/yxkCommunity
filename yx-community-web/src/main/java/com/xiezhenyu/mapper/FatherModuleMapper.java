package com.xiezhenyu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiezhenyu.entity.FatherModuleVo;
import com.xiezhenyu.model.FatherModuleDo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author Tim
 */
@Mapper
@Repository
public interface FatherModuleMapper extends BaseMapper<FatherModuleDo> {

    /**
     * 查询所有父板块信息
     * @param limit
     * @param offset
     * @return
     */
    public ArrayList<FatherModuleVo> selectListFatherModuleVo(Integer limit, Integer offset);

}
