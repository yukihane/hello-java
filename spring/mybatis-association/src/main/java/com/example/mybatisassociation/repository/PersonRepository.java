package com.example.mybatisassociation.repository;

import com.example.mybatisassociation.entity.Person;
import java.util.Optional;

public interface PersonRepository {

    Optional<Person> findById(long id);
}
