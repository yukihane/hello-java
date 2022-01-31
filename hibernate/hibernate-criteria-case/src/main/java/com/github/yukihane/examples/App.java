package com.github.yukihane.examples;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 */
public class App {

    public static void main(final String[] args) throws SQLException {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session sess = sf.openSession();
        Transaction tx = sess.beginTransaction();
        sess.createSQLQuery("create table my_table(id integer, name varchar(255))").executeUpdate();
        sess.createSQLQuery("insert into my_table(id,name) values(1,'yamada'),(2,'tanaka'),(3,'suzuki'),(4,'inoue')").executeUpdate();
        tx.commit();
        sf.close();
    }
}
