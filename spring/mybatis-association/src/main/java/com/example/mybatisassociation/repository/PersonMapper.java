package com.example.mybatisassociation.repository;

import com.example.mybatisassociation.entity.Person;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonMapper {
    Optional<Person> findById(long id);
}
