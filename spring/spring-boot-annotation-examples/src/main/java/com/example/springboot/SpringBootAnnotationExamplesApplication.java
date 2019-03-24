package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * {@link SpringBootApplication} を付与したクラス(便宜上メインクラスと呼びます)を
 * {@link SpringApplication#run(Class, String...)} メソッド引数に指定します。
 *
 * {@link SpringBootApplication} には {@link ComponentScan}
 * が付与されており、これより下に位置するパッケージがSpringコンポーネントとしてスキャンされるので、
 * 通常メインクラスはパッケージ階層のトップレベルに置きます。
 */
@SpringBootApplication
@EnableSwagger2
public class SpringBootAnnotationExamplesApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SpringBootAnnotationExamplesApplication.class, args);
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
