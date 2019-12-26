package com.blbz.fundooapi.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    @NotNull
    @Column(name = "user_fname")
    private String fname;
    @NotNull
    @Column(name = "user_lname")
    private String lname;
    @NotNull
    @Column(name = "user_email")
    private String eid;
    @NotNull
    @Column(name = "user_phn")
    private String phn;
    @NotNull
    @Column(name = "user_address")
    private String adrs;
    @NotNull
    @Column(name = "user_pass")
    private String pas;

    public UserInfo() {
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "user_id=" + user_id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", eid='" + eid + '\'' +
                ", phn='" + phn + '\'' +
                ", adrs='" + adrs + '\'' +
                ", pas='" + pas + '\'' +
                '}';
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
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
        this.pas = pas;
    }
}

