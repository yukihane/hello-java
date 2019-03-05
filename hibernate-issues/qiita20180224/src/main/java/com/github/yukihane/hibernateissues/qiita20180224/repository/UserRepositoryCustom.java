package com.github.yukihane.hibernateissues.qiita20180224.repository;

import com.github.yukihane.hibernateissues.qiita20180224.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;

public interface UserRepositoryCustom {

    @EntityGraph(value = "user", attributePaths = "orders", type = EntityGraph.EntityGraphType.LOAD)
    Optional<User> findOne(String userNo);

}
