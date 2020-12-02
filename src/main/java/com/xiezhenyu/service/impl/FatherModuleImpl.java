package com.xiezhenyu.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.mapper.FatherModuleMapper;
import com.xiezhenyu.model.FatherModuleDo;
import com.xiezhenyu.service.IFatherModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tim
 */
@Service
public class FatherModuleImpl implements IFatherModuleService {

    @Autowired
    private FatherModuleMapper fatherModuleMapper;

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
    public Page<FatherModuleDo> selectList(Integer limit, Integer offset) {
        Page<FatherModuleDo> fatherModuleDoPage = fatherModuleMapper.selectPage(new Page<>(limit, offset), null);
        return fatherModuleDoPage;
    }

    @Override
    public FatherModuleDo selectById(Long id) {
        return fatherModuleMapper.selectById(id);
    }

}
