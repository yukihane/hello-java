package com.example.oauth2server.service;

import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;

import com.example.oauth2server.entity.Task;

public interface TaskFindService {

    @PostFilter("filterObject.creator.name == authentication.name")
    List<Task> findAll();

    @PostAuthorize("returnObject.creator.name == authentication.name")
    Task findById(Long id);

}
