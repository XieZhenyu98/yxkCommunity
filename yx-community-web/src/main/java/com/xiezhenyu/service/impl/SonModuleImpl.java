package com.xiezhenyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.mapper.SonModuleDoMapper;
import com.xiezhenyu.mapper.SonModuleMapper;
import com.xiezhenyu.entity.SonModuleVo;
import com.xiezhenyu.model.SonModuleDo;
import com.xiezhenyu.query.SonModuleQuery;
import com.xiezhenyu.service.IFatherModuleService;
import com.xiezhenyu.service.ISonModuleService;
import com.xiezhenyu.service.IUserService;
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
    @Autowired
    private SonModuleDoMapper sonModuleDoMapper;
    @Autowired
    private IFatherModuleService fatherModuleService;
    @Autowired
    private IUserService userService;

    @Override
    public boolean insert(SonModuleDo sonModuleDo) {
        int num = sonModuleDoMapper.insert(sonModuleDo);
        return num > 0;
    }

    @Override
    public ArrayList<SonModuleVo> selectList(Integer limit, Integer offset) {
        ArrayList<SonModuleVo> sonModuleVos = sonModuleMapper.selectListPage(limit,offset);
        return sonModuleVos;
    }

    @Override
    public boolean update(SonModuleDo sonModuleDo) {
        int sum = sonModuleDoMapper.updateById(sonModuleDo);
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

    @Override
    public Page<SonModuleDo> getPage(SonModuleQuery sonModuleQuery) {
        Page<SonModuleDo> page = null;
        if(sonModuleQuery.getFatherModuleId()!=null){
            page = sonModuleDoMapper.selectPage(new Page<>(sonModuleQuery.getPageNo(), sonModuleQuery.getPageSize()),
                    new QueryWrapper<SonModuleDo>().like("module_name", sonModuleQuery.getModuleName())
                            .eq("father_module_id",sonModuleQuery.getFatherModuleId()));
        }else {
            page = sonModuleDoMapper.selectPage(new Page<>(sonModuleQuery.getPageNo(), sonModuleQuery.getPageSize()),
                    new QueryWrapper<SonModuleDo>().like("module_name", sonModuleQuery.getModuleName()));
        }
        for(SonModuleDo sonModuleDo : page.getRecords()) {
            sonModuleDo.setUserDo(userService.getUserById(sonModuleDo.getUserId()));
            sonModuleDo.setFatherModuleDo(fatherModuleService.selectById(sonModuleDo.getFatherModuleId()));
        }
        return page;
    }

    @Override
    public boolean deleteSonModule(SonModuleDo sonModuleDo) {
        sonModuleDoMapper.deleteById(sonModuleDo.getId());
        return true;
    }
}
