package com.blbz.springframwork.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;


@Component
public class NextClass {
    @Autowired
    private MyDetail myDetail;
    @Resource
    private HashMap<String, String> map;

    public MyDetail getMyDetail() {
        return myDetail;
    }

    public void setMyDetail(MyDetail myDetail) {
        this.myDetail = myDetail;
    }

    @Override
    public String toString() {
        getMapValue();
        return "next class" + myDetail;
    }

    public void getMapValue() {
        this.map.forEach((k, v) -> {
            if (k.equals("age")) {
                myDetail.setAge(Integer.parseInt(v));
            }else if (k.equals("name")){
                myDetail.setName(v);
            }
        });
        System.out.println(myDetail);
    }

    public String returningString(String d){
        return d;
    }

    public String throwblemethod() throws IOException {
        throw new IOException();
    }

    public int forArrount(int i) throws IOException {
        return i;
    }
    /*public int forArrount(int i) throws IOException {
        throw new IOException();

    }*/
}
