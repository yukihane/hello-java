package com.github.yukihane.mybatisassociation;

import com.github.yukihane.mybatisassociation.entity.Person;
import com.github.yukihane.mybatisassociation.repository.PersonRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class MybatisAssociationApplication implements CommandLineRunner {

    private final PersonRepository personRepository;

    public static void main(final String[] args) {
        SpringApplication.run(MybatisAssociationApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
        System.out.println("hello, world");
        final Optional<Person> p1 = personRepository.findById(11);
        System.out.println(p1);
    }

}
