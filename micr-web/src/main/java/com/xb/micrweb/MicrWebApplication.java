package com.xb.micrweb;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.xb.common.Util.JwtUtil;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDubbo
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class MicrWebApplication {
    @Value("${jwt.secret}")
    private String secretKey;
    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil(secretKey);
    }

    public static void main(String[] args) {
        SpringApplication.run(MicrWebApplication.class, args);
    }

}
