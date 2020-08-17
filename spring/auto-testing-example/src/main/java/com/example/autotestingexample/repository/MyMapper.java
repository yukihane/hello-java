package com.example.autotestingexample.repository;

import com.example.autotestingexample.entity.MyTable;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MyMapper {

    @Insert("insert into my_table (message) values (#{message})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(MyTable myTable);

    @Select("select * from my_table")
    MyTable select();

    @Delete("delete from my_table")
    void delete();
}
