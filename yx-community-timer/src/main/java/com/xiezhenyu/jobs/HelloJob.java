package com.xiezhenyu.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author Tim
 * @date 2021/3/24
 */
public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("这里可以执行我们的业务逻辑");
    }
}
