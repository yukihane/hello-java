package com.example.oauth2client.controller;

import com.example.oauth2client.repository.TaskRepository;
import com.example.oauth2server.entity.Task;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tasks")
@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskRepository repository;

    @GetMapping
    String list(final Model model) {
        final List<Task> taskList = repository.findAll();
        model.addAttribute(taskList);
        return "task/list";
    }
}