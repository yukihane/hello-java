package com.github.yukihane.springtestconnectionsize;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringTestConnectionSizeApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    public void contextLoads() {
        final List<Connection> conns = new ArrayList<>();
        // デフォルトのコネクションプール数は10なので失敗する
        for (int i = 0; i < 2000; i++) {
            final Connection c = DataSourceUtils.getConnection(dataSource);
            conns.add(c);
            System.out.println(c);
        }

    }

}
