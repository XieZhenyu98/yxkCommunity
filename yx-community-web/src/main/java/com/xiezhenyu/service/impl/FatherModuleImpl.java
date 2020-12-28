package com.xiezhenyu.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.entity.FatherModuleVo;
import com.xiezhenyu.mapper.FatherModuleMapper;
import com.xiezhenyu.model.FatherModuleDo;
import com.xiezhenyu.service.IFatherModuleService;
import com.xiezhenyu.service.ISonModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tim
 */
@Service
public class FatherModuleImpl implements IFatherModuleService {

    @Autowired
    private FatherModuleMapper fatherModuleMapper;

    @Autowired
    private ISonModuleService sonModuleService;

    @Override
    public FatherModuleDo add(FatherModuleDo fatherModuleDo) {
        fatherModuleMapper.insert(fatherModuleDo);
        return fatherModuleMapper.selectById(fatherModuleDo.getId());
    }

    @Override
    public boolean deleteById(Long id) {
        int num = fatherModuleMapper.deleteById(id);
        return num > 0;
    }

    @Override
    public FatherModuleDo update(FatherModuleDo fatherModuleDo) {
        fatherModuleMapper.updateById(fatherModuleDo);
        return fatherModuleMapper.selectById(fatherModuleDo.getId());
    }

    @Override
    public Page<FatherModuleVo> selectList(Integer limit, Integer offset) {
        Page page = new Page();
        ArrayList<FatherModuleVo> fatherModuleVos = fatherModuleMapper.selectListFatherModuleVo(limit, offset);
        page.setRecords(fatherModuleVos);
        return page;
    }

    @Override
    public FatherModuleDo selectById(Long id) {
        return fatherModuleMapper.selectById(id);
    }

}