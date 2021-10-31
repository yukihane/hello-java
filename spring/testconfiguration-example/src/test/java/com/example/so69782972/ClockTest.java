package com.example.so69782972;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ClockTest {

    private static final ZoneId TOKYO = ZoneId.of("Asia/Tokyo");

    @TestConfiguration
    static class TestDateTimeConfig {
        @Bean
        // // If @Primary is added, succeeds.
        // @Primary
        public Clock clockMock() {
            final LocalDate date = LocalDate.of(2010, 8, 15);
            final Instant fixedInstant = date.atStartOfDay(TOKYO).toInstant();
            final Clock clock = Clock.fixed(fixedInstant, TOKYO);

            return clock;
        }
    }

    @Autowired
    private Clock clock;

    @Test
    public void stopTheWorld() {
        assertThat(clock).isNotNull();

        final ZonedDateTime now = Instant.now(clock).atZone(TOKYO);
        assertThat(now.getYear()).isEqualTo(2010);
    }
}
