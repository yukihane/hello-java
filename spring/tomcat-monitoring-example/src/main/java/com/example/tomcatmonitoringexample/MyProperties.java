package com.example.tomcatmonitoringexample;

import java.time.Duration;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

// https://docs.spring.io/spring-boot/docs/2.5.1/reference/htmlsingle/#features.external-config.typesafe-configuration-properties.constructor-binding
@ConfigurationProperties("my.service")
@ConstructorBinding
@Data
@RequiredArgsConstructor
public class MyProperties {

    // https://docs.spring.io/spring-boot/docs/2.5.1/reference/htmlsingle/#features.external-config.typesafe-configuration-properties.conversion
    private final Duration metricsStep;
}
