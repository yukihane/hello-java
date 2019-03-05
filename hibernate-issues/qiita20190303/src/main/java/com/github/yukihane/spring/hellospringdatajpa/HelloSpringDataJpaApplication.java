package com.github.yukihane.spring.hellospringdatajpa;

import com.github.yukihane.spring.hellospringdatajpa.entity.UserEntity;
import com.github.yukihane.spring.hellospringdatajpa.repository.UserRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class HelloSpringDataJpaApplication implements CommandLineRunner {

    @Autowired
    private UserRepository repository;

    public static void main(final String[] args) {
        SpringApplication.run(HelloSpringDataJpaApplication.class, args);
    }

    @Transactional
    @Override
    public void run(final String... args) throws Exception {
        final Calendar cal = Calendar.getInstance();
        final Date now = cal.getTime();
        cal.roll(Calendar.YEAR, -1);
        final Date past = cal.getTime();
        cal.roll(Calendar.YEAR, 1);
        final Date future = cal.getTime();

        final List<UserEntity> users = Stream.generate(() -> new UserEntity(now)).limit(100)
                .collect(Collectors.toList());
        repository.saveAll(users);
        repository.flush();

//        final int res = repository.deleteByBasedateBetween(past, future);
        final int res = repository.deletByBasedateRange(past, future);

        System.out.println("deleted: " + res);
    }

}
