package com.xiezhenyu.config;

import com.xiezhenyu.filter.AdminJwtFilter;
import com.xiezhenyu.filter.SimpleCORSFilter;
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
    public FilterRegistrationBean simpleCORSFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(simpleCORSFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("simpleCORSFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public FilterRegistrationBean jwtFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(adminJwtFilter());
        registration.addUrlPatterns("/admin/*");
        registration.setName("adminJwtFilter");
        registration.setOrder(2);
        return registration;
    }

    @Bean(name = "simpleCORSFilter")
    public Filter simpleCORSFilter() {
        return new SimpleCORSFilter();
    }
    @Bean(name = "adminJwtFilter")
    public Filter adminJwtFilter() {
        return new AdminJwtFilter();
    }
}
