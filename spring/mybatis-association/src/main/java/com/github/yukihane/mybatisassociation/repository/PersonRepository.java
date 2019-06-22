package com.github.yukihane.mybatisassociation.repository;

import com.github.yukihane.mybatisassociation.entity.Person;
import java.util.Optional;

public interface PersonRepository {

    Optional<Person> findById(long id);
}
