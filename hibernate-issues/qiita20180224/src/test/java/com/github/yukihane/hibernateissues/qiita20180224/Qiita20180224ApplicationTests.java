package com.github.yukihane.hibernateissues.qiita20180224;

import com.github.yukihane.hibernateissues.qiita20180224.entity.Order;
import com.github.yukihane.hibernateissues.qiita20180224.entity.User;
import com.github.yukihane.hibernateissues.qiita20180224.repository.UserRepository;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Qiita20180224ApplicationTests {

    @Autowired
    private UserRepository repository;

    @Test
    public void contextLoads() {
        User user = new User();
        user.setUserNo("no_01");
        Order order = new Order();

        User savedUser = repository.save(user);
        savedUser.setOrders(Arrays.asList(order));
    }

}
