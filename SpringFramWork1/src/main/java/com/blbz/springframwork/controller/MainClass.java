package com.blbz.springframwork.controller;

import com.blbz.springframwork.model.MyClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
    public static void main(String[] args) {
        System.out.println("Spring Frame work example");
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        MyClass my=context.getBean("mydetail",MyClass.class);
        System.out.println(my);
    }
}
