package com.example.mybatisassociation.repository;

import com.example.mybatisassociation.entity.Sex;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SexMapper {

    Optional<Sex> findById(long id);

    List<Sex> findAll();
}
