package com.example.autotestingexample;

import lombok.Data;
import org.junit.jupiter.api.Test;

/**
 * テストコードでもLombok(annotation processor)が利用できることのテスト
 */
public class InContainerLombokUsingTest {

    @Data
    public static class TestData {
        private String name;
    }

    @Test
    public void useLombokTest() {
        final TestData data = new TestData();
        data.setName("Hello, world!");
    }
}
