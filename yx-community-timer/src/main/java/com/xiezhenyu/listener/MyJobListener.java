package com.xiezhenyu.listener;

import com.xiezhenyu.service.QuartzJobService;
import com.xiezhenyu.service.impl.QuartzJobServiceImpl;
import com.xiezhenyu.utils.SpringUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Tim
 * @date 2021/3/24
 */
public class MyJobListener implements JobListener {
    private String name;
    @Override
    public String getName() {
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    /**
     * 执行前
     * @param jobExecutionContext
     */
    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {

    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {

    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        if(e == null){
            // 任务正常结束
            QuartzJobService quartzJobService = SpringUtil.getBean(QuartzJobService.class);
            quartzJobService.updateLastRunTime(jobExecutionContext.getJobDetail().getKey().getName());
        }
    }
}
