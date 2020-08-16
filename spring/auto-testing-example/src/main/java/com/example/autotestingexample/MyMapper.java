package com.example.autotestingexample;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MyMapper {

    @Insert("insert into my_table (id, message) values (#{id},#{message})")
    void insert(MyTable instance);

    @Select("select * from my_table")
    MyTable select();
}
