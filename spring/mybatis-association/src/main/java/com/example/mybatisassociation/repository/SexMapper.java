package com.example.mybatisassociation.repository;

import com.example.mybatisassociation.entity.Sex;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SexMapper {

    @Select("select * from sex where id = #{id}")
    Optional<Sex> findById(long id);

    @Select("select * from sex")
    List<Sex> findAll();
}
