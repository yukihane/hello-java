package com.github.yukihane.hibernateissues.qiita20180224;

import static org.junit.Assert.assertEquals;

import com.github.yukihane.hibernateissues.qiita20180224.entity.Child;
import com.github.yukihane.hibernateissues.qiita20180224.entity.Parent;
import com.github.yukihane.hibernateissues.qiita20180224.repository.ParentRepository;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyApplicationTests {

    @Autowired
    private ParentRepository repository;

    // @Transactional をコメントアウトすると LIE
    @Transactional
    @Test
    public void contextLoads() {
        List<Parent> res = repository.findAll();
        assertEquals(2, res.size());
        Parent p = res.stream().filter(e -> e.getId()==1001).findFirst().get();

        assertEquals(2, p.getChildren().size());
        Child c1 = p.getChildren().stream().filter(c -> c.getId() == 1).findFirst().get();
        assertEquals("child content 1", c1.getContent());
    }

}
