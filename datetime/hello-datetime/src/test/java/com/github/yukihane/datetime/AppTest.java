package com.github.yukihane.datetime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.chrono.JapaneseChronology;
import java.time.chrono.JapaneseDate;
import java.time.chrono.JapaneseEra;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void localdate_of() {
        // 平成31年6月12日
        assertThatThrownBy(() -> JapaneseDate.of(JapaneseEra.HEISEI, 31, 6, 12));
    }

    @Test
    public void japanesechronology() {
        final Map<TemporalField, Long> fieldValues = new HashMap<>();
        fieldValues.put(ChronoField.ERA, (long) JapaneseEra.HEISEI.getValue());
        fieldValues.put(ChronoField.YEAR_OF_ERA, 31L);
        fieldValues.put(ChronoField.MONTH_OF_YEAR, 6L);
        fieldValues.put(ChronoField.DAY_OF_MONTH, 12L);
        final JapaneseDate today = JapaneseChronology.INSTANCE.resolveDate(fieldValues, ResolverStyle.LENIENT);
        System.out.println(today);

        assertThat(today.get(ChronoField.YEAR)).isEqualTo(2019);
        assertThat(today.get(ChronoField.MONTH_OF_YEAR)).isEqualTo(6);
        assertThat(today.get(ChronoField.DAY_OF_MONTH)).isEqualTo(12);
    }
}
