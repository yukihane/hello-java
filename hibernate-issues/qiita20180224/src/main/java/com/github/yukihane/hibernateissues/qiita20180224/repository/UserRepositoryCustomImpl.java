package com.github.yukihane.hibernateissues.qiita20180224.repository;

import com.github.yukihane.hibernateissues.qiita20180224.entity.User;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @Autowired
    private EntityManager em;

    @Override
    public Optional<User> findOne(String userNo) {
        User res = em.find(User.class, userNo);
        return Optional.ofNullable(res);
    }
}
