package com.petcare.Model;

import java.sql.Date;

public class Service {
    private int id;
    private String price;
    private Date date;
    private String state;
    private String type;
    private Date startTime;
    private Date endTime;
    private int ownerId;


    public Service() {
    }

    public Service(int id, String price, Date date, String state, String type, Date startTime, Date endTime, int ownerId) {
        this.id = id;
        this.price = price;
        this.date = date;
        this.state = state;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.ownerId = ownerId;

    }

    public Service(String type, Date today, Date startTime, Date endTime, int ownerId) {
        this.type = type;
        this.date = today;
        this.startTime = startTime;
        this.endTime = endTime;
        this.ownerId = ownerId;

    }

    // Getter and setter methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }


}

