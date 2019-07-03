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
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(final Long id) {
        return taskRepository.findById(id).orElse(null);
    }

}
