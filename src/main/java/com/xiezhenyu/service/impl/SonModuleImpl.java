package com.xiezhenyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiezhenyu.mapper.SonModuleMapper;
import com.xiezhenyu.entity.SonModuleVo;
import com.xiezhenyu.service.ISonModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tim
 */
@Service
public class SonModuleImpl implements ISonModuleService {

    @Autowired
    private SonModuleMapper sonModuleMapper;

    @Override
    public boolean insert(SonModuleVo sonModuleVo) {
        int num = sonModuleMapper.insert(sonModuleVo);
        return num > 0;
    }

    @Override
    public ArrayList<SonModuleVo> selectList(Integer limit, Integer offset) {
        ArrayList<SonModuleVo> sonModuleVos = sonModuleMapper.selectListPage(limit,offset);
        return sonModuleVos;
    }

    @Override
    public boolean update(SonModuleVo sonModuleVo) {
        int sum = sonModuleMapper.update(sonModuleVo);
        return sum > 0;
    }

    @Override
    public SonModuleVo selectById(Long id) {
        return sonModuleMapper.selectById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        int num = sonModuleMapper.deleteById(id);
        return num > 0;
    }

    @Override
    public ArrayList<SonModuleVo> selectListByFatherId(Long id) {
        List<SonModuleVo> list = new ArrayList<>();
        list = sonModuleMapper.selectList(new QueryWrapper<SonModuleVo>().eq("father_module_id", id));
        return (ArrayList<SonModuleVo>)list;
    }
}
