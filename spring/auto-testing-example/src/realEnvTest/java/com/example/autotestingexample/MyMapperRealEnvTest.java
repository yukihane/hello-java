package com.example.autotestingexample;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class MyMapperRealEnvTest {

    @Autowired
    private MyMapper mapper;

    @Test
    @Transactional
    void test() {

        final MyTable res = mapper.select();
        assertThat(res.getId()).isGreaterThan(0L);
        // 起動時刻が所定フォーマットで格納されている
        assertThat(res.getMessage()).matches(Pattern.compile("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}"));
    }

}
