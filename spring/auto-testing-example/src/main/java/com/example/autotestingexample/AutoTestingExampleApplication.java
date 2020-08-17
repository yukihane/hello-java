package com.example.autotestingexample;

import com.example.autotestingexample.domain.MyService;
import com.example.autotestingexample.entity.MyTable;
import com.example.autotestingexample.repository.MyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@RequiredArgsConstructor
public class AutoTestingExampleApplication implements CommandLineRunner {

    private final MyMapper myMapper;
    private final MyService myService;

    public static void main(final String[] args) {
        SpringApplication.run(AutoTestingExampleApplication.class, args);
    }

    @Override
    @Transactional
    public void run(final String... args) throws Exception {
        // 起動時刻を格納する

        myMapper.delete();

        final MyTable table = new MyTable();
        table.setMessage(myService.getNow());
        myMapper.insert(table);
    }

}
