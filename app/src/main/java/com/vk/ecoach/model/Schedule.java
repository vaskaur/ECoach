package com.vk.ecoach.model;

import java.io.Serializable;

public class Schedule implements Serializable {

    public String busnum;
    public String date;
    public String time;
    public String type;
    public String docId;

    public Schedule() {
    }

    public Schedule(String busnum, String date, String type) {
        this.busnum = busnum;
        this.date = date;
        this.type = type;
    }

    public Schedule(String busnum, String date, String time, String type) {
        this.busnum = busnum;
        this.date = date;
        this.time = time;
        this.type = type;
    }

    public Schedule(String busnum, String date, String time, String type, String docId) {
        this.busnum = busnum;
        this.date = date;
        this.time = time;
        this.type = type;
        this.docId = docId;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "busnum='" + busnum + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", type='" + type + '\'' +
                ", docId='" + docId + '\'' +
                '}';
    }
}
