package com.github.yukihane.spring.mybatisdatetime;

import java.time.ZoneOffset;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class MyBatisTypeHandlerConfig {

    private final ZoneOffset offset;

    @Bean
    public OffsetDateTimeTypeHandler offsetDateTimeTypeHandler() {
        return new OffsetDateTimeTypeHandler(offset);
    }
}
