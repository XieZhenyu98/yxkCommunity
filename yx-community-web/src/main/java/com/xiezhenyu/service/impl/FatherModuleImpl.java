package com.xiezhenyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.entity.FatherModuleVo;
import com.xiezhenyu.entity.SonModuleVo;
import com.xiezhenyu.mapper.FatherModuleMapper;
import com.xiezhenyu.model.FatherModuleDo;
import com.xiezhenyu.query.FatherModuleQuery;
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

    @Override
    public Page<FatherModuleDo> getPage(FatherModuleQuery fatherModuleQuery) {
        Page<FatherModuleDo> fatherModules = fatherModuleMapper.selectPage(new Page<>(fatherModuleQuery.getPageNo(), fatherModuleQuery.getPageSize()),
                new QueryWrapper<FatherModuleDo>().like("module_name", fatherModuleQuery.getModuleName()).orderByDesc("sort"));
        return fatherModules;
    }

    @Override
    public boolean deleteModule(FatherModuleDo fatherModuleDo) {
        ArrayList<SonModuleVo> sonList = sonModuleService.selectListByFatherId(fatherModuleDo.getId());
        if(sonList==null || sonList.size()==0) {
            fatherModuleMapper.deleteById(fatherModuleDo.getId());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<FatherModuleDo> getAll() {
        List<FatherModuleDo> fatherModuleDos = fatherModuleMapper.selectList(null);
        return fatherModuleDos;
    }

}
