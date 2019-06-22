package com.example.mybatisassociation.repository;

import com.example.mybatisassociation.entity.Sex;
import java.util.List;
import java.util.Optional;

public interface SexRepository {

    Optional<Sex> findById(long id);

    List<Sex> findAll();

}
