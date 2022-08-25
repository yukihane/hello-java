package com.github.yukihane.examples;

import static org.jooq.codegen.maven.example.Tables.AUTHOR;

import java.sql.Connection;
import java.sql.DriverManager;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;

/**
 * Hello world!
 */
@Slf4j
public class App {

    public static void main(final String[] args) {

        log.info("info log");
        log.debug("debug log");

        final String userName = "root";
        final String password = "mypw";
        final String url = "jdbc:mysql://localhost:3306/library";

        // Connection is the only JDBC resource that we need
        // PreparedStatement and ResultSet are handled by jOOQ, internally
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            // https://www.jooq.org/doc/latest/manual/sql-building/dsl-context/custom-settings/
            final Settings settings = new Settings();
            settings.setExecuteLogging(true);

            final DSLContext create = DSL.using(conn, SQLDialect.MYSQL, settings);
            final Result<Record> result = create.select().from(AUTHOR).fetch();
        }

        // For the sake of this tutorial, let's keep exception handling simple
        catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
