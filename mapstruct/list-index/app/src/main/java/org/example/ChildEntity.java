package org.example;

public class ChildEntity {
    private int index;
    private String name;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ChildEntity{" +
                "index=" + index +
                ", name='" + name + '\'' +
                '}';
    }
}
