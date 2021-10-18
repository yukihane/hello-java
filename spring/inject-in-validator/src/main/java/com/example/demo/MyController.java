package com.example.demo;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyController {

    private final ConversionService conversionService;
    private final MyEntityRepository repository;

    // https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-requestbody
    @PostMapping("/")
    public MyEntity index(@Valid @RequestBody final MyRequest req) {

        final MyEntity myEntity = conversionService.convert(req, MyEntity.class);
        final MyEntity saved = repository.save(myEntity);
        return saved;
    }

    public record MyRequest(@NotNull @NotBlank @MyConstraint("1") String name, int age) {
    }

    @PostMapping("/2")
    public MyEntity index2(@Valid @RequestBody final MyRequest2 req) {
        return new MyEntity();
    }

    public record MyRequest2(@NotNull @NotBlank @MyConstraint("2") String name, int age) {
    }

    @Mapper
    public interface EntityMapper extends Converter<MyRequest, MyEntity> {
        @Override
        @Mapping(target = "id", ignore = true)
        MyEntity convert(MyRequest req);
    }
}
