package com.xiezhenyu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author Tim
 * @date 2020/12/15
 */
@Configuration
@EnableSwagger2 //开启Swagger
public class SwaggerConfig {

    public static final Contact DEFAULT_CONTACT = new Contact("谢振瑜", "xiezhenyu98.github.io", "751811152@qq.com");

    // 配置Swagger的Docket的bean实例

    @Bean
    public Docket docket(Environment environment) {

//        Profiles profiles = Profiles.of("dev");

//        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("以轩社区")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xiezhenyu.controller"))
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "以轩社区的SwaggerAPI文档",
                "加油吧，少年！",
                "v1.0",
                "https://github.com/XieZhenyu98",
                DEFAULT_CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0");
    }

}
