package com.xiezhenyu.service.impl;

import com.xiezhenyu.mapper.SonModuleMapper;
import com.xiezhenyu.model.SonModuleDo;
import com.xiezhenyu.service.ISonModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author Tim
 */
@Service
public class SonModuleImpl implements ISonModuleService {

    @Autowired
    private SonModuleMapper sonModuleMapper;

    @Override
    public boolean insert(SonModuleDo sonModuleDo) {
        int num = sonModuleMapper.insert(sonModuleDo);
        return num > 0;
    }

    @Override
    public ArrayList<SonModuleDo> selectList(Integer limit,Integer offset) {
        ArrayList<SonModuleDo> sonModuleDos = sonModuleMapper.selectList(limit,offset);
        return sonModuleDos;
    }

    @Override
    public boolean update(SonModuleDo sonModuleDo) {
        int sum = sonModuleMapper.update(sonModuleDo);
        return sum > 0;
    }

    @Override
    public SonModuleDo selectById(Long id) {
        return sonModuleMapper.selectById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        int num = sonModuleMapper.deleteById(id);
        return num > 0;
    }
}
