package com.example.demo;

import java.util.Map;
import javax.validation.Validator;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// JPA フレームワークが利用する validator インスタンス生成を Spring Framework に任せる設定
@Configuration
public class MyConfig {

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(final Validator validator) {
        return new HibernatePropertiesCustomizer() {
            @Override
            public void customize(final Map<String, Object> hibernateProperties) {
                hibernateProperties.put("javax.persistence.validation.factory", validator);
            }
        };
    }
}