package com.example.oauth2server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.oauth2server.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
