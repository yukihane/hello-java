package com.example.springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.springboot.entity.MyEntity;

/**
 * MyBatisの {@link Mapper} は、Mapped Statementをバインドするためのインターフェイスです。
 * <a href=
 * "http://www.mybatis.org/spring-boot-starter/">mybatis-spring-boot-starter</a>
 * を利用しているので、組み込まれた
 * <a href=
 * "http://www.mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/index.html">mybatis-spring-boot-autoconfigure</a>
 * によって <code>Mapper</code> は<a href=
 * "https://github.com/mybatis/spring-boot-starter/blob/mybatis-spring-boot-2.0.0/mybatis-spring-boot-autoconfigure/src/main/java/org/mybatis/spring/boot/autoconfigure/MybatisAutoConfiguration.java#L210">デフォルトでコンポーネントスキャン対象となります</a>。
 *
 * @see <a href=
 * "http://www.mybatis.org/mybatis-3/ja/getting-started.html#Mapper_">MyBatis
 * スタートガイド - Mapper インスタンス</a>
 */
@Mapper
public interface MyEntityMapper {

    MyEntity find(long id);
}
