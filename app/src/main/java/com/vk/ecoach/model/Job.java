package com.vk.ecoach.model;

import java.io.Serializable;

public class Job implements Serializable {

    public String busnojob;
    public String date;
    public String comments;
    public String docId;

    public Job(String busnojob, String date, String comments) {
        this.busnojob = busnojob;
        this.date = date;
        this.comments = comments;
    }

    public Job(String busnojob, String date, String comments, String docId) {
        this.busnojob = busnojob;
        this.date = date;
        this.comments = comments;
        this.docId = docId;
    }

    public Job() {
    }

    @Override
    public String toString() {
       // return "    Comments:'" + comments + '\'';
        String message = "Comments: "+comments;

        return message;
    }

   /* @Override
    public String toStringj() {

        String message = "Bus No.:"+busnojob+"\nComments:"+comments;

        return message;
    }*/

}
