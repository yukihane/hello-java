package com.example.mybatisassociation;

import com.example.mybatisassociation.entity.Person;
import com.example.mybatisassociation.repository.PersonRepository;
import com.example.mybatisassociation.repository.SexRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class MybatisAssociationApplication implements CommandLineRunner {

    private final PersonRepository personRepository;
    private final SexRepository sexRepository;

    public static void main(final String[] args) {
        SpringApplication.run(MybatisAssociationApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
        System.out.println("hello, world");
        System.out.println(sexRepository.findById(1L));
        final Optional<Person> p1 = personRepository.findById(11L);
        System.out.println(p1);
    }

}
