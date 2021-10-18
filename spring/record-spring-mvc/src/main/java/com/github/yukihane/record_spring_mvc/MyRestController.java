package com.github.yukihane.record_spring_mvc;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
@Slf4j
public class MyRestController {

    private final ConversionService conversionService;

    @PostMapping
    public ResponseData rest(@RequestBody final RequestData req) {
        log.info("req: {}", req);
        final ResponseData resp = conversionService.convert(req, ResponseData.class);
        log.info("resp: {}", resp);
        return resp;
    }

    public record RequestData(
        String name,
        int age,
        LocalDate registrationDate) {
    }

    public record ResponseData(
        String name,
        int age,
        LocalDate registrationDate) {
    }

    @Mapper
    public interface RequestDataMapper extends Converter<RequestData, ResponseData> {
        @Override
        ResponseData convert(RequestData source);
    }
}
