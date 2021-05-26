package com.xiezhenyu.config;

import com.xiezhenyu.filter.AdminJwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author Tim
 * @date 2021/5/26
 */
@Configuration
public class FilterConfiguration {
    @Bean
    public FilterRegistrationBean jwtFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(adminJwtFilter());
        registration.addUrlPatterns("/admin/*");
        registration.setName("adminJwtFilter");
        registration.setOrder(2);
        return registration;
    }

    @Bean(name = "adminJwtFilter")
    public Filter adminJwtFilter() {
        return new AdminJwtFilter();
    }
}
