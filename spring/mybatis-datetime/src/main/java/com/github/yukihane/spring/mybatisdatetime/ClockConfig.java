package com.github.yukihane.spring.mybatisdatetime;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClockConfig {

    @Bean
    public Clock clock() {
        final ZoneId tokyo = ZoneId.of("Asia/Tokyo");
        final LocalDate date = LocalDate.of(2010, 10, 10);
        final Instant fixedInstant = date.atStartOfDay(tokyo).toInstant();
        final Clock clock = Clock.fixed(fixedInstant, tokyo);

        return clock;
    }
}
