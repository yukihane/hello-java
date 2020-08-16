package com.example.autotestingexample;

import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyClockConfig {
    /**
     * @see https://yukihane.github.io/blog/201905/31/spring-boot-bean-on-testing/
     */
    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
