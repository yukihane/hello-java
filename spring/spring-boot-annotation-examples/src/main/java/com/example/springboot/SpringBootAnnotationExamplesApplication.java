package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * {@link SpringBootApplication} を付与したクラス(便宜上メインクラスと呼びます)を
 * {@link SpringApplication#run(Class, String...)} メソッド引数に指定します。
 *
 * {@link SpringBootApplication} には {@link ComponentScan}
 * が付与されており、これより下に位置するパッケージがSpringコンポーネントとしてスキャンされるので、
 * 通常メインクラスはパッケージ階層のトップレベルに置きます。
 */
@SpringBootApplication
public class SpringBootAnnotationExamplesApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SpringBootAnnotationExamplesApplication.class, args);
    }

}
