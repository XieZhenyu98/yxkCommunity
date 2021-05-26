package com.xiezhenyu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiezhenyu.model.admin.RoleMenuRelation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Tim
 */
@Repository
@Mapper
public interface RoleMenuRelationMapper extends BaseMapper<RoleMenuRelation> {
}
