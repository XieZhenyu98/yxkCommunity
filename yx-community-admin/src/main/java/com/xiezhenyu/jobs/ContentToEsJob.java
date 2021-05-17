package com.xiezhenyu.jobs;

import com.xiezhenyu.service.ContentToEsService;
import com.xiezhenyu.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author Tim
 * @date 2021/5/17
 */
@Slf4j
public class ContentToEsJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("开始执行帖子es同步...");
        ContentToEsService bean = SpringUtil.getBean(ContentToEsService.class);
        bean.contentToEs();
        log.info("帖子es同步执行成功...");
    }
}
