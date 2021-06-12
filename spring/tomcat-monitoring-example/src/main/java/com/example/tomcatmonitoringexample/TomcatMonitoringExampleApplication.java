package com.example.tomcatmonitoringexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(MyProperties.class)
public class TomcatMonitoringExampleApplication {

    public static void main(final String[] args) {
        SpringApplication.run(TomcatMonitoringExampleApplication.class, args);
    }

}
