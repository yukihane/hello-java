package com.example.oauth2server.service;

import java.util.List;

import com.example.oauth2server.entity.Task;

public interface TaskFindService {

    List<Task> findByName(String name);

    Task findByNameAndId(String name, Long id);

}
