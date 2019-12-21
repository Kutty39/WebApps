package com.blbz.springmvc.model;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserSession {
    String user;
    List<String> pages;

    @Override
    public String toString() {
        StringBuilder detail = new StringBuilder("user='" + user + "\nPage visited:\n");
        for (String st : pages) {
            detail.append(st);
        }
        return detail.toString();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<String> getPages() {
        return pages;
    }

    public void setPages(List<String> pages) {
        this.pages = pages;
    }
}
