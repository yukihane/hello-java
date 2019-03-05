package com.github.yukihane.spring.hellospringdatajpa.repository;

import com.github.yukihane.spring.hellospringdatajpa.entity.UserEntity;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Integer> {

    int deleteByBasedateBetween(Date start, Date end);
}
