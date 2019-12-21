package com.blbz.springframwork.model;

import org.springframework.stereotype.Component;

public class MyDetail {
    private String name;
    private int age;

    public MyDetail() {
    }

    @Override
    public String toString() {
        return "MyDetail{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public MyDetail(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
