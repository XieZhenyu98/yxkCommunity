package com.xiezhenyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.mapper.RegistrationMapper;
import com.xiezhenyu.model.RegistrationDo;
import com.xiezhenyu.service.IRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Tim
 */
@Service
public class RegistrationServiceImpl implements IRegistrationService {

    @Autowired
    private RegistrationMapper registrationMapper;

    @Override
    public boolean add(RegistrationDo registrationDo) {
        registrationDo.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        boolean todayRegistration = isTodayRegistration(registrationDo.getUserId());
        if(!todayRegistration){
            return false;
        }
        int num = registrationMapper.insert(registrationDo);
        return num > 0;
    }

    @Override
    public boolean isTodayRegistration(Long userId) {
        String todayDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        List<Object> dates = registrationMapper.selectObjs(
                new QueryWrapper<RegistrationDo>()
                        .likeRight("date", todayDate)
                        .eq("user_id",userId)
        );
        return dates.isEmpty();
    }

    @Override
    public Page<RegistrationDo> list(Long userId, Integer limit, Integer offset) {
        return registrationMapper.selectPage(new Page<RegistrationDo>(limit, offset), new QueryWrapper<RegistrationDo>().eq("user_id", userId));
    }

    @Override
    public List<RegistrationDo> selectByDate(Long userId, String startDate, String endDate) {
        List<RegistrationDo> list = registrationMapper.selectList(
                new QueryWrapper<RegistrationDo>()
                        .eq("user_id", userId)
                        .between("date", startDate, endDate)
        );
        return list;
    }

    @Override
    public Integer countByUserId(Long userId) {
        Integer count = registrationMapper.selectCount(
                new QueryWrapper<RegistrationDo>()
                        .eq("user_id", userId)
        );
        return count;
    }
}
