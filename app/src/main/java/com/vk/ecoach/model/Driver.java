package com.vk.ecoach.model;

import java.io.Serializable;

public class Driver implements Serializable {

    public String did;
    public String name;
    public String phone;
    public String email;
    public String password;
    public String joining;
    public String attendance;
    public String Token;


    public Driver() {
    }

    public Driver(String did, String name, String phone, String email, String password, String joining, String attendance) {
        this.did = did;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.joining = joining;
        this.attendance = attendance;
    }

    public Driver(String did, String name, String phone, String email, String password, String joining, String attendance, String token) {
        this.did = did;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.joining = joining;
        this.attendance = attendance;
        Token = token;
    }
}
