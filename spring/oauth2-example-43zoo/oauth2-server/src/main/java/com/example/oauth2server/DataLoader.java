package com.example.oauth2server;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.oauth2server.entity.Task;
import com.example.oauth2server.entity.User;
import com.example.oauth2server.repository.TaskRepository;
import com.example.oauth2server.repository.UserRepository;

import lombok.RequiredArgsConstructor;

// https://stackoverflow.com/a/38049582
@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        final List<User> users = Arrays.asList(
            new User(null, "yamada", "pwyamada"),
            new User(null, "suzuki", "pwsuzuki"));

        userRepository.saveAll(users);

        final User yamada = userRepository.findByName("yamada").get();
        final User suzuki = userRepository.findByName("suzuki").get();

        final List<Task> tasks = Arrays.asList(
            new Task(null, yamada, "yamadaのtask 1"),
            new Task(null, yamada, "yamadaのtask 2"),
            new Task(null, suzuki, "suzukiのtask"));

        taskRepository.saveAll(tasks);

    }

}
