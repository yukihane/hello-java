package com.example.autotestingexample.repository;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 余計なbean定義をスキャンしてしまうのを防ぐためのダミークラス。
 *
 * @see <a href=
 * "http://mybatis.org/spring-boot-starter/mybatis-spring-boot-test-autoconfigure/#Prevent_detecting_a_real_.40SpringBootApplication">
 * Prevent detecting a real @SpringBootApplication</a>
 */
@SpringBootApplication
class MapperTestApplication {
}
