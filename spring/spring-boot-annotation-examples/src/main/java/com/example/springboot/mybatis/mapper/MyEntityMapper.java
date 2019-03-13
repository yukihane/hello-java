package com.example.springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.springboot.entity.MyEntity;

@Mapper
public interface MyEntityMapper {

    MyEntity find(long id);
}
