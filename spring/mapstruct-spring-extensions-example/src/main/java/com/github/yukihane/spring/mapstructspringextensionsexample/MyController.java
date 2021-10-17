package com.github.yukihane.spring.mapstructspringextensionsexample;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MyController {

    private final ConversionService conversionService;

    @PostMapping("/")
    public CarDto index(@RequestBody final Car car) {
        log.info("car: {}", car);

        final CarDto dto = conversionService.convert(car, CarDto.class);
        log.info("dto: {}", dto);

        return dto;
    }

}
