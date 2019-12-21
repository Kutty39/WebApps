package com.blbz.springframwork.controller;

import com.blbz.springframwork.model.MyDetail;
import com.blbz.springframwork.model.NextClass;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;


public class MyApp {


    public static void main(String[] args) {
        //using Beanfactory
        Resource resource = new ClassPathResource("spring.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        MyDetail md = factory.getBean("mydetail", MyDetail.class);
        System.out.println("Using Bean Factory: " + md);

        //using application context
        ApplicationContext Context = new ClassPathXmlApplicationContext("spring.xml");
        md = Context.getBean("mydetail", MyDetail.class);
        System.out.println("Using Application Context: " + md);

        //Using Autowired
        NextClass nx = Context.getBean("nextClass", NextClass.class);
        System.out.println("Autowired with @Component " + nx);
        System.out.println("@Resource with map");
        System.out.println("****************************************************");
        System.out.println("Aspect Oriented Programming");
        System.out.println("****************************************************");
        nx.getMapValue();
        System.out.println("****************************************************");
        nx.returningString("selvan");
        System.out.println("****************************************************");
        try {
            nx.throwblemethod();
        } catch (IOException e) {
            System.out.println("just throwing");
        }
        System.out.println("****************************************************");
        try {
            nx.forArrount(10);
        } catch (NullPointerException|IOException e) {
            System.out.println("just throwing");
        }
        System.out.println("****************************************************");
    }

}
