package com.blbz.springframwork.model;

public class MyClass {
    private String name;
    private int age;
    private String location;

    public MyClass() {
    }

    public MyClass(String name, int age, String location) {
        this.name = name;
        this.age = age;
        this.location = location;
    }

    @Override
    public String toString() {
        return "MyClass{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", location='" + location + '\'' +
                '}';
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
