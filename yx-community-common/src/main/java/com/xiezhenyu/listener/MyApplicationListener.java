package com.xiezhenyu.listener;

import com.xiezhenyu.utils.SpringUtil;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author Tim
 * @date 2021/3/24
 */
public class MyApplicationListener implements ApplicationListener<ApplicationPreparedEvent> {
    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        SpringUtil.setApplicationContext(event.getApplicationContext());
    }
}
