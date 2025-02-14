package com.example.clock_bean

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Clock
import java.time.ZoneId

@Configuration(proxyBeanMethods = false)
class ClockConfig {

    @Bean
    fun clock(): Clock {
        return Clock.system(ZoneId.of("Asia/Tokyo"))
    }
}
