package com.example.tetteispringexample.user.repository;

import com.example.tetteispringexample.user.domain.User;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserRepository {
    @Select("select * from usr where userId = #{username}")
    Optional<User> findById(String username);
}
