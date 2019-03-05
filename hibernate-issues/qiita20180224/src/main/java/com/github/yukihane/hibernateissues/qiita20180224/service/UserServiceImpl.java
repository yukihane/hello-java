package com.github.yukihane.hibernateissues.qiita20180224.service;

import com.github.yukihane.hibernateissues.qiita20180224.entity.User;
import com.github.yukihane.hibernateissues.qiita20180224.repository.UserRepository;
import java.util.Optional;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EntityManager em;

    @Override
    public User getUser(String userNo) {
        Optional<User> optional = userRepository.findOne(userNo);
        User result = optional.get();
        em.detach(result);
        return result;
    }
}