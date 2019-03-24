package com.example.springboot.repository;

import java.util.Optional;

import com.example.springboot.entity.MyEntity;

public interface MyRepository {

    Optional<MyEntity> find(long id);

    MyEntity insert(MyEntity entity);

}
