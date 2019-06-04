package com.github.yukihane.spring.mybatisdatetime;

import java.time.Clock;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/data")
public class MyDataController {

    private final MyDataMapper mapper;

    @GetMapping
    public String data() {
        final String zipCode = mapper.selectZipcode();
        final String name = mapper.selectName();

        return "zip: " + zipCode + ", name: " + name;
    }

    @Mapper
    public interface MyDataMapper {

        @Select("select zipcode from my_data")
        String selectZipcode();

        @Select("select name from my_data")
        String selectName();
    }
}
