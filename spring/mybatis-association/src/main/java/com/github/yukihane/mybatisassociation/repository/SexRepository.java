package com.github.yukihane.mybatisassociation.repository;

import com.github.yukihane.mybatisassociation.entity.Sex;
import java.util.List;
import java.util.Optional;

public interface SexRepository {

    Optional<Sex> findById(long id);

    List<Sex> findAll();
}
