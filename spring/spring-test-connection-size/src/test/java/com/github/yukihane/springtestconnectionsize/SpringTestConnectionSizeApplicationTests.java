package com.github.yukihane.springtestconnectionsize;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringTestConnectionSizeApplicationTests {

    private static org.h2.tools.Server server;

    @Autowired
    private DataSource dataSource;

    @BeforeClass
    public static void beforeClass() throws SQLException {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                shutdownServer();
            }
        }));

        server = org.h2.tools.Server.createTcpServer("-ifNotExists", "-tcpPort", "9092", "-tcpAllowOthers").start();
    }

    @AfterClass
    public static void afterClass() {
        shutdownServer();
    }

    private static void shutdownServer() {
        if (server != null) {
            server.shutdown();
            server = null;
        }
    }

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
