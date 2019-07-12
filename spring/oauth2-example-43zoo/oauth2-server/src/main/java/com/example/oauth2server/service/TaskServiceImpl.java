package com.example.oauth2server.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.oauth2server.entity.Task;
import com.example.oauth2server.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskFindService {

    private final TaskRepository taskRepository;

    @Override
    public List<Task> findByName(final String name) {
        return taskRepository.findByUserName(name);
    }

    @Override
    public Task findByNameAndId(final String name, final Long id) {
        return taskRepository.findByIdAndUserName(id, name).orElse(null);
    }

}
