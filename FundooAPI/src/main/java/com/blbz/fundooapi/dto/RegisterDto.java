package com.blbz.fundooapi.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RegisterDto {

    @NotNull(message = "First Name should not be null")
    private String fname;
    @NotNull(message = "Last Name should not be null")
    private String lname;
    @NotNull(message = "Email ID should not be null")
    @Pattern(regexp = "^[a-zA-z\\d._-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,4}$",message = "Please enter valid Email")
    private String eid;
    @NotNull(message = "Phone Number should not be null")
    @Pattern(regexp = "^[0-9]{10}$",message = "Enter valid phone number")
    private String phn;
    @NotNull(message = "Address should not be null")
    private String adrs;
    @NotNull(message = "Password should not be null")
    @Pattern(regexp = "^((?=.*[a-z])(?=.*[A-Z])(?!.*\\s)(?=.*[@$!%*?&])).{8,15}$",
            message = "Password is combination of Upper case,Lower" +
            "case,Number and Special Character it should be min length of 8 and " +
            "Max 15")
    @Size(min = 8,max = 15)
    private String pas;
    @NotNull(message = "Confirm Password should not be null")
    private String conpas;

    @Override
    public String toString() {
        return "RegisterDto{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", eid='" + eid + '\'' +
                ", phn='" + phn + '\'' +
                ", adrs='" + adrs + '\'' +
                '}';
    }

    public String getConpas() {
        return conpas;
    }

    public void setConpas(String conpas) {
        this.conpas = conpas;
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
