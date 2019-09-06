package com.example.mybatismapperexample;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MybatisMapperExampleApplication implements CommandLineRunner {

    @Autowired
    private GreetingMapper mapper;

    public static void main(final String[] args) {
        SpringApplication.run(MybatisMapperExampleApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
        final Optional<Greeting> gr01 = mapper.findById("02");
        System.out.println(gr01);

        final List<Greeting> hello = mapper.findByMessage(Message.of("Hello!"));
        System.out.println(hello);

        final long count = mapper.deleteByMessage(Message.of("Hello!"));
        System.out.println("Deleted: " + count);

        final Greeting obj = new Greeting();
        obj.setId("10");
        obj.setMessage(Message.of("Bye!"));
        mapper.insert(obj);

        final long delCount = mapper.deleteByMessage(Message.of("Bye!"));
        System.out.println("Deleted: " + delCount);
    }

}
