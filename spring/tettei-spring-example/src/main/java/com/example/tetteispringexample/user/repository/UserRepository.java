package com.example.tetteispringexample.user.repository;

import com.example.tetteispringexample.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
