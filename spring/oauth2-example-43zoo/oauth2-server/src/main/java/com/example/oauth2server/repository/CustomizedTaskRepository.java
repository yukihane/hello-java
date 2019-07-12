package com.example.oauth2server.repository;

import java.util.List;
import java.util.Optional;

import com.example.oauth2server.entity.Task;

public interface CustomizedTaskRepository {

    List<Task> findByUserName(String name);

    Optional<Task> findByIdAndUserName(Long id, String name);

}
