package com.example.mybatismapperexample;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GreetingMapper {

    Optional<Greeting> findById(String id);

    List<Greeting> findByMessage(Message message);

    void insert(Greeting greeting);

    long deleteByMessage(Message message);
}
