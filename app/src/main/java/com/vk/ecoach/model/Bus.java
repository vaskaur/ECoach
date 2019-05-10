package com.vk.ecoach.model;

import java.io.Serializable;

public class Bus implements Serializable {

    public int id;
    public String docId;
    public String busno;
    public String km;
    public String battery;
    public String engine;
    public String onroute;
    public String mnfyear;
    public String airfilter;
    public String engineoil;
    public String dieselfilter;
    public String aservicedate;
    public String aservicedue;
    public String breaks;
    public String bearing;
    public String bservicedate;
    public String bservicedue;
    public String grease;
    //date of last service and type
    public Bus(int id, String busno, String km, String battery, String engine, String onroute, String mnfyear) {
        this.id = id;
        this.busno = busno;
        this.km = km;
        this.battery = battery;
        this.engine = engine;
        this.onroute = onroute;
        this.mnfyear = mnfyear;
    }

    public Bus(int id, String docId, String busno, String km, String battery, String engine, String onroute, String mnfyear, String airfilter, String engineoil, String dieselfilter, String aservicedate, String aservicedue, String breaks, String bearing, String bservicedate, String bservicedue, String grease) {
        this.id = id;
        this.docId = docId;
        this.busno = busno;
        this.km = km;
        this.battery = battery;
        this.engine = engine;
        this.onroute = onroute;
        this.mnfyear = mnfyear;
        this.airfilter = airfilter;
        this.engineoil = engineoil;
        this.dieselfilter = dieselfilter;
        this.aservicedate = aservicedate;
        this.aservicedue = aservicedue;
        this.breaks = breaks;
        this.bearing = bearing;
        this.bservicedate = bservicedate;
        this.bservicedue = bservicedue;
        this.grease = grease;
    }

    public Bus(int id, String docId, String busno, String km, String battery, String engine, String onroute, String mnfyear) {
        this.id = id;
        this.docId = docId;
        this.busno = busno;
        this.km = km;
        this.battery = battery;
        this.engine = engine;
        this.onroute = onroute;
        this.mnfyear = mnfyear;
    }

    public Bus() {
    }

   /* public String toStringg(){
        String data="Bus No. :"+busno+"KM :"+km+"On Route :"+onroute;
        return data;

    }
*/

    public String toString() {
        String message= "Bus no:"+busno+
                "km:"+km+
                "Battery:"+battery+
                "Engine:"+engine+
                "Onroute:"+ onroute+
                "Mnf year:"+mnfyear+
                "Air filter:"+airfilter+
                "Engine oil: " + engineoil +
                "Diesel filter: " + dieselfilter +
                "A Service date: " + aservicedate +
                "A Service due: " + aservicedue +
                "Breaks: " + breaks +
                "Bearing: " + bearing +
                "Grease: " + grease +
                "B Service date: " + bservicedate +
                "B Service due: " + bservicedue;

        return message;
    }
}
