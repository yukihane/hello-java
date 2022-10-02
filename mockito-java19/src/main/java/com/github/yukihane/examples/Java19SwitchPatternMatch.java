package com.github.yukihane.examples;

import java.util.LinkedList;

/**
 * Hello world!
 */
public class Java19SwitchPatternMatch {
    public String func(final LinkedList<Object> o) {
        final var obj = o.get(0);
        return switch (obj) {
            case final String s when s.length() > 5 -> "too long text";
            case null -> "null";
            default -> obj.toString();
        };
    }
}
