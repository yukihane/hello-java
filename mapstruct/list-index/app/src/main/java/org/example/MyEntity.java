package org.example;

import java.util.HashSet;
import java.util.Set;

public class MyEntity {
    private String name;
    private Set<ChildEntity> children = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ChildEntity> getChildren() {
        return children;
    }

    public void setChildren(Set<ChildEntity> children) {
        this.children = children;
    }
}
