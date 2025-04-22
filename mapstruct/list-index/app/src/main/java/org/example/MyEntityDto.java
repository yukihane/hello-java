package org.example;

import java.util.ArrayList;
import java.util.List;

public class MyEntityDto {
    private String name;
    private List<ChildEntityDto> children = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChildEntityDto> getChildren() {
        return children;
    }

    public void setChildren(List<ChildEntityDto> children) {
        this.children = children;
    }
}
