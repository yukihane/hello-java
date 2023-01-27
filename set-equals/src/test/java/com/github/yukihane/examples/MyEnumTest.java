package com.github.yukihane.examples;

import org.junit.Test;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class MyEnumTest {

    @Test
    public void test() {
        EnumSet<MyEnum> target = EnumSet.of(MyEnum.ORANGE);

        Set<MyEnum> set = new HashSet<>();
        assertFalse(target.equals(set));

        set.add(MyEnum.ORANGE);
        assertTrue(target.equals(set));

        set.add(MyEnum.APPLE);
        assertFalse(target.equals(set));
    }

}