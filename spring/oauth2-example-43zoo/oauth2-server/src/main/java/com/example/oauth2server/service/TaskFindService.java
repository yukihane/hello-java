package com.example.oauth2server.service;

import java.util.List;

import com.example.oauth2server.entity.Task;

public interface TaskFindService {

    List<Task> findAll();

    Task findById(Long id);

}
