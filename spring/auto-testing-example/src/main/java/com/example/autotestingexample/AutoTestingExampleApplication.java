package com.example.autotestingexample;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@RequiredArgsConstructor
public class AutoTestingExampleApplication implements CommandLineRunner {

    private final MyMapper myMapper;
    private final MyService myService;

    public static void main(final String[] args) {
        SpringApplication.run(AutoTestingExampleApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
        final MyTable table = new MyTable();
        table.setMessage(myService.getNow());
        myMapper.insert(table);
    }

}
