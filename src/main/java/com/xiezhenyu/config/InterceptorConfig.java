package com.xiezhenyu.config;

import com.xiezhenyu.interceptors.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Tim
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                // 登录接口不用于token验证
                .excludePathPatterns("/user/login")
                // 其他非登录接口都需要进行token验证
                .addPathPatterns("/user/**");
    }
}
