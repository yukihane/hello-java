package com.github.yukihane.springboot.oauth2clientrestsample;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Oauth2ClientRestSampleApplication implements CommandLineRunner {

    public static void main(final String[] args) {
        SpringApplication.run(Oauth2ClientRestSampleApplication.class, args);
    }

    private final BookRepository repository;

    @Override
    public void run(final String... args) throws Exception {
        repository.save(Book.builder().title("吾輩は猫である").build());
        repository.save(Book.builder().title("城の崎にて").build());
        repository.save(Book.builder().title("夏への扉").build());
    }

}
