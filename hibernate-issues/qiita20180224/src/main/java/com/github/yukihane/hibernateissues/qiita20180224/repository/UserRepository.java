package com.github.yukihane.hibernateissues.qiita20180224.repository;

import com.github.yukihane.hibernateissues.qiita20180224.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {
}