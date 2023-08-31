package com.xb.micrweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguation {
    @Bean
    public Docket docket(){
        // 文档类型 2.0版本
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("盈利宝项目")
                .version("1.0")
                .description("盈利宝前后端分离微服务项目,前端vue,后端采用Springboot+dubbo分布式系统")
                .build();
        docket.apiInfo(apiInfo);
        // 指定注解
        docket = docket.select()
                .apis(RequestHandlerSelectors.basePackage("com.xb.micrweb.controller")).build();
        return docket;
    }

}
