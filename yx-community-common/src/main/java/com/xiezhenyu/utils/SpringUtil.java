package com.xiezhenyu.utils;

import org.springframework.context.ApplicationContext;

/**
 * @author Tim
 * @date 2021/3/24
 */
public class SpringUtil {
    private static ApplicationContext applicationContext = null;
    /**
     * 设置applicationContext
     * @param applicationContext
     */
    public static synchronized void setApplicationContext(ApplicationContext applicationContext){
        if( SpringUtil.applicationContext ==null ){
            SpringUtil.applicationContext = applicationContext;
        }
    }

    /**
     * 获取ApplicationContext
     * @return
     */
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    /**
     * 通过class获取Bean
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }
}
