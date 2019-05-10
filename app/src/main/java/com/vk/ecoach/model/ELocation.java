package com.vk.ecoach.model;

import java.io.Serializable;

public class ELocation implements Serializable {

public double latitude;
public double longitude;
public String address;
public String issue;

    public ELocation() {
    }

    public ELocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public ELocation(double latitude, double longitude, String address) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    public ELocation(double latitude, double longitude, String address, String issue) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.issue = issue;
    }
}
