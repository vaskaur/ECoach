package com.vk.ecoach.model;

import java.io.Serializable;

public class Worker implements Serializable {

    public String password;
    public String wid;
    public String name;
    public String phone;
    public String email;
    public String department;
    public String joining;
    public String attendance;

    public Worker() {
    }

    public Worker(String wid, String name, String phone, String email, String password, String department, String joining, String attendance) {
        this.wid = wid;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.department = department;
        this.joining = joining;
        this.attendance = attendance;
    }

}
