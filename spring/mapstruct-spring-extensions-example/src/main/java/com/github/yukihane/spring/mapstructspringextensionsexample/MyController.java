package com.github.yukihane.spring.mapstructspringextensionsexample;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MyController {

    private final CarMapper carMapper;

    @PostMapping("/")
    public CarDto index(@RequestBody final Car car) {
        log.info("car: {}", car);

        final CarDto dto = carMapper.convert(car);
        log.info("dto: {}", dto);

        return dto;
    }

}
