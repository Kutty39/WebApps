package com.blbz.springmvc.model;

import com.blbz.springmvc.entity.UserInfo;
import com.blbz.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegDetail {
    @Autowired
    private UserInfo userInfo;
    @Autowired
    private UserService userService;
    private String fname;
    private String lname;

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    private String eid;
    private String phn;
    private String adrs;
    private String pas;
    private String conpas;

    public RegDetail() {
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", eid='" + eid + '\'' +
                ", phn='" + phn + '\'' +
                ", adrs='" + adrs + '\'' +
                ", pas='" + pas + '\'' +
                ", conpas='" + conpas + '\'' +
                '}';
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }


    public String getPhn() {
        return phn;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }

    public String getAdrs() {
        return adrs;
    }

    public void setAdrs(String adrs) {
        this.adrs = adrs;
    }

    public String getPas() {
        return pas;
    }

    public void setPas(String pas) {
        this.pas = userService.encript(pas);
    }

    public String getConpas() {
        return conpas;
    }

    public void setConpas(String conpas) {
        this.conpas = conpas;
    }

    public UserInfo createUser() {
        userInfo.setFname(fname);
        userInfo.setAdrs(adrs);
        userInfo.setEid(eid);
        userInfo.setLname(lname);
        userInfo.setPas(pas);
        userInfo.setPhn(phn);
        return userInfo;
    }
}
