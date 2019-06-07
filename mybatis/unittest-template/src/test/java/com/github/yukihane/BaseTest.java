/**
 *    Copyright 2009-2019 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.github.yukihane;

import java.io.Reader;

import org.apache.ibatis.BaseDataTest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BaseTest {

    private static SqlSessionFactory sqlSessionFactory;

    @BeforeAll
    static void setUp() throws Exception {
        // create an SqlSessionFactory
        try (Reader reader = Resources.getResourceAsReader("mybatis-config.xml")) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }

        // populate in-memory database
        BaseDataTest.runScript(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(),
            "CreateDB.sql");
    }

    @Test
    void shouldGetAUser() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            final Mapper mapper = sqlSession.getMapper(Mapper.class);
            final User user = mapper.getUser(1);
            Assertions.assertEquals("User1", user.getName());
        }
    }

    @Test
    void shouldInsertAUser() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            final Mapper mapper = sqlSession.getMapper(Mapper.class);
            final User user = new User();
            user.setId(2);
            user.setName("User2");
            mapper.insertUser(user);
        }
    }

    interface Mapper {

        @Select("select * from users where id = #{id}")
        User getUser(Integer id);

        @Insert("insert into users values(#{id}, #{name})")
        void insertUser(User user);

    }
}