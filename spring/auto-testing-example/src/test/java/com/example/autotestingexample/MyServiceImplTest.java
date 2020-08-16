package com.example.autotestingexample;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyServiceImplTest {

    private static final ZoneId TOKYO = ZoneId.of("Asia/Tokyo");

    private MyServiceImpl sut;

    @BeforeEach
    public void setUp() {
        final LocalDate date = LocalDate.of(2010, 8, 15);
        final Instant fixedInstant = date.atStartOfDay(TOKYO).toInstant();
        final Clock clock = Clock.fixed(fixedInstant, TOKYO);

        sut = new MyServiceImpl(clock);
    }

    @Test
    void testGetToday() {
        final String res = sut.getToday();
        assertThat(res).isEqualTo("2010-08-15");
    }

}
