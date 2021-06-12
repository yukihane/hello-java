package com.example.tomcatmonitoringexample;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.logging.LoggingMeterRegistry;
import io.micrometer.core.instrument.logging.LoggingRegistryConfig;
import java.time.Duration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    /**
     * @see <a href="https://kazuhira-r.hatenablog.com/entry/2021/05/03/230700">
     * Spring Boot Actuatorで、Micrometerで収集したメトリクスをログに出力する - CLOVER🍀</a>
     */
    @Bean
    public MeterRegistry meterRegistry(final MyProperties props) {
        final LoggingRegistryConfig config = new LoggingRegistryConfig() {
            @Override
            public String get(final String key) {
                return null;
            }

            @Override
            public Duration step() {
                return props.getMetricsStep();
            }
        };

        return new LoggingMeterRegistry(config, Clock.SYSTEM);
    }
}
