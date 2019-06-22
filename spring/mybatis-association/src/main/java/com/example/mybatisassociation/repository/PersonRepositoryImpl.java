package com.example.mybatisassociation.repository;

import com.example.mybatisassociation.entity.Person;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PersonRepositoryImpl implements PersonRepository {

    private final PersonMapper mapper;

    @Override
    public Optional<Person> findById(final long id) {
        return mapper.findById(id);
    }
}
