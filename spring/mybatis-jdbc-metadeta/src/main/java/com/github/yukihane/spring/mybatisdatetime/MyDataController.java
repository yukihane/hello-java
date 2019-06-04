package com.github.yukihane.spring.mybatisdatetime;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MyDataController {

    private final MyDataMapper mapper;

    @GetMapping("/data")
    public String data() {
        final String zipCode = mapper.selectZipcode();
        final String name = mapper.selectName();

        return "zip: " + zipCode + ", name: " + name;
    }

    @GetMapping("/update")
    public String update() {
        mapper.updateName("name");
        mapper.updateZipcode("1234567");

        return "updated";
    }

    @Mapper
    public interface MyDataMapper {

        @Select("select zipcode as zc from my_data")
        String selectZipcode();

        @Update("update my_data set zipcode = #{zipcode}")
        void updateZipcode(String zipcode);

        @Select("select name from my_data")
        String selectName();

        @Update("update my_data set name = #{name}")
        void updateName(String name);
    }
}
