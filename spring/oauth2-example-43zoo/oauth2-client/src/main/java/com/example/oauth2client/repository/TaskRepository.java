package com.example.oauth2client.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestOperations;

import com.example.oauth2server.entity.Task;

@Repository
public class TaskRepository {

    private final RestOperations restOperations;
    private final String resourcesUrl;
    private final String resourceUrlTemplate;

    TaskRepository(final RestOperations restOperations,
        @Value("${api.url}/tasks") final String resourcesUrl) {
        this.restOperations = restOperations;
        this.resourcesUrl = resourcesUrl;
        this.resourceUrlTemplate = resourcesUrl + "/{id}";
    }

    public List<Task> findAll() {
        return Arrays.asList(restOperations.getForObject(resourcesUrl, Task[].class));
    }

    public Task findOne(final long id) {
        return restOperations.getForObject(resourceUrlTemplate, Task.class, id);
    }
}