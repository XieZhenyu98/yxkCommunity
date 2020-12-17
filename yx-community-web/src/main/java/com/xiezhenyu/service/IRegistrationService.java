package com.xiezhenyu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.model.RegistrationDo;

import java.util.List;

/**
 * @author Tim
 */
public interface IRegistrationService {

    /**
     * 签到
     * @param registrationDo
     * @return
     */
    public boolean add(RegistrationDo registrationDo);

    /**
     * 今天是否签到
     * @param userId
     * @return
     */
    public boolean isTodayRegistration(Long userId);

    /**
     * 分页查询签到情况
     * @param userId
     * @param limit
     * @param offset
     * @return
     */
    public Page<RegistrationDo> list(Long userId, Integer limit, Integer offset);

    /**
     * 通过开始日期和结束日期查询签到情况
     * @param userId
     * @param startDate
     * @param endDate
     * @return
     */
    public List<RegistrationDo> selectByDate(Long userId, String startDate, String endDate);

    /**
     * 通过用户ID查询该用户累计签到情况
     * @param userId
     * @return
     */
    public Integer countByUserId(Long userId);

}
