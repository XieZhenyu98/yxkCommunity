package com.xiezhenyu.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;

/**
 * @author Tim
 * @date 2021/3/24
 */
public class MyJobFactory extends AdaptableJobFactory {
    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        // 调用父类的方法实例化对象
        Object jobInstance = super.createJobInstance(bundle);
        // 进行注入
        capableBeanFactory.autowireBean(jobInstance);
        // 返回对象
        return jobInstance;
    }
}
