package com.xiezhenyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiezhenyu.entity.QuartzJob;
import com.xiezhenyu.mapper.QuartzJobMapper;
import com.xiezhenyu.service.QuartzJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Tim
 * @date 2021/3/24
 */
@Service
public class QuartzJobServiceImpl implements QuartzJobService {
    @Autowired
    QuartzJobMapper quartzJobMapper;
    @Override
    public QuartzJob selectQuartzJob(Integer id) {
        QuartzJob quartzJob = quartzJobMapper.selectById(id);
        return quartzJob;
    }

    @Override
    public void updateLastRunTime(String jobName) {
        QuartzJob quartzJob = new QuartzJob();
        quartzJob.setJobName(jobName);
        quartzJob.setLastRunTime(new Date());
        quartzJobMapper.update(quartzJob,new QueryWrapper<QuartzJob>().eq("job_name",jobName));
    }
}
