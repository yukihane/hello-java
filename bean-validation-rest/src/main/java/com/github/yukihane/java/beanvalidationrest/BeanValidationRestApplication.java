package com.github.yukihane.java.beanvalidationrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BeanValidationRestApplication {

    public static void main(final String[] args) {
        SpringApplication.run(BeanValidationRestApplication.class, args);
    }

    @Bean
    public Docket document() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        final ApiInfo apiInfo = new ApiInfo("Sample API", "", "terms of service", "", "", "", "");
        return apiInfo;
    }

}
