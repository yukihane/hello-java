package com.example.autotestingexample.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.autotestingexample.entity.MyTable;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * {@code @MybatisTest} はデフォルトでインメモリDBを利用したテストになります。
 * 余計なbean定義をスキャンしないように {@link MapperTestApplication} で防いでいます。
 *
 * @see <a href=
 * "http://mybatis.org/spring-boot-starter/mybatis-spring-boot-test-autoconfigure/">
 * mybatis-spring-boot-test-autoconfigure</a>
 * @see <a href="https://stackoverflow.com/a/42730944/4506703">
 * &#064;JdbcTest detect class annotated &#064;SpringBootApplication -
 * Stack Overflow</a>
 * @see MapperTestApplication
 */
@MybatisTest
class MyMapperInContainerTest {

    @Autowired
    private MyMapper mapper;

    @Autowired
    private JdbcTemplate template;

    @Test
    void test1() {
        test();
    }

    // ロールバックされているのを確認するために、2回同じテストを実行している
    @Test
    void test2() {
        test();
    }

    private void test() {
        final MyTable obj = mapper.select();
        // 起動時の処理は実行されないので 何もインサートされていない
        assertThat(obj).isNull();

        final MyTable myTable = new MyTable();
        myTable.setMessage("test-message");
        mapper.insert(myTable);

        final List<Map<String, Object>> res = template.queryForList("select * from my_table");
        assertThat(res.size()).isEqualTo(1);
        final Map<String, Object> row = res.iterator().next();

        final Object id = row.get("id");
        assertThat(id).isInstanceOf(Long.class);
        assertThat((Long) id).isGreaterThan(0L);

        final Object message = row.get("message");
        assertThat(message).isInstanceOf(String.class);
        assertThat((String) message).isEqualTo("test-message");
    }

}
