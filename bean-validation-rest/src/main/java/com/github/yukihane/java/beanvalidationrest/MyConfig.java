package com.github.yukihane.java.beanvalidationrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

    @Configuration
    public class MyConfig {

        @Autowired
        private MessageSource messageSource;

        @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
            final LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
            factoryBean.setValidationMessageSource(messageSource);
            return factoryBean;
        }
    }
