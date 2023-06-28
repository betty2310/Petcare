package com.petcare.Model;

import java.sql.Date;

/**
 * This class is model of all records on pet (also information about service)
 */
public class RecordOnPet {
    private String name;
    private Date date;
    private String type;
    private String status;
    private String state;

    public RecordOnPet() {
    }

    public RecordOnPet(String name, Date date, String type, String status, String stage) {
        this.name = name;
        this.date = date;
        this.type = type;
        this.state = stage;
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
