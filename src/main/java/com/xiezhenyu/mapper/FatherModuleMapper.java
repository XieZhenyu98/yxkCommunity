package com.xiezhenyu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiezhenyu.model.FatherModuleDo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Tim
 */
@Mapper
@Repository
public interface FatherModuleMapper extends BaseMapper<FatherModuleDo> {

}
