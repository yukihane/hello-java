package com.github.yukihane.spring.mapstructspringextensionsexample;

import org.mapstruct.extensions.spring.SpringMapperConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SpringMapperConfig
public class MapstructSpringExtensionsExampleApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MapstructSpringExtensionsExampleApplication.class, args);
    }

}
