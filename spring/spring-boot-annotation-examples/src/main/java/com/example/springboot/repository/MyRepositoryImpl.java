package com.example.springboot.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springboot.entity.MyEntity;
import com.example.springboot.mybatis.mapper.MyEntityMapper;

/**
 * {@link Repository} は、 (DDDで言うところの)
 */
@Repository
public class MyRepositoryImpl implements MyRepository {

    @Autowired
    private MyEntityMapper mapper;

    @Override
    public Optional<MyEntity> find(final long id) {
        return Optional.ofNullable(mapper.find(id));
    }

}
