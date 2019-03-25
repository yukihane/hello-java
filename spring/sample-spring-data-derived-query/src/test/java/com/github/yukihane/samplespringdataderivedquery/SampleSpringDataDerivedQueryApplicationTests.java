package com.github.yukihane.samplespringdataderivedquery;

import com.github.yukihane.samplespringdataderivedquery.repository.MyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleSpringDataDerivedQueryApplicationTests {

    @Autowired
    private MyRepository repository;

    @Test
    public void testFindById() {
        repository.findById(1L);
    }

    @Test
    public void testFindByName() {
        repository.findByName("noname");
    }

}
