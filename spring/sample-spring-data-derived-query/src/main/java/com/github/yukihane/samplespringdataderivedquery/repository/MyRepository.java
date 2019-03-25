package com.github.yukihane.samplespringdataderivedquery.repository;

import com.github.yukihane.samplespringdataderivedquery.entity.MyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyRepository extends JpaRepository<MyEntity, Long> {
}
