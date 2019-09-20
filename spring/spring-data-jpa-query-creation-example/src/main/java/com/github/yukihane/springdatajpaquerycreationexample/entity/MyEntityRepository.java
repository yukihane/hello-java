package com.github.yukihane.springdatajpaquerycreationexample.entity;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyEntityRepository extends JpaRepository<MyEntity, String> {
    long deleteByMyEntityIdAndAltIdNot(String id, String altId);

    long deleteByMyEntityIdIn(Collection<String> ids);
}
