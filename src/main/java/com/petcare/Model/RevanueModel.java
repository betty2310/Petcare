package com.petcare.Model;

import java.util.Date;

public class RevanueModel {
    private int id;

    private String type;
    private String price;
    private Date date_begin;
    private Date date;

    private  Date date_end;

    public RevanueModel(int id,String type, String price, Date date, Date date_begin, Date date_end) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.date = date;
        this.date_begin = date_begin;
        this.date_end = date_end;
    }

    public RevanueModel() {
    }

    public String getId() {
        return Integer.toString(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public void setDate(Date date_begin) {
        this.date = date;
    }

    public Date getDate_begin() {
        return date_begin;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public void setDate_begin(Date date_end) {
        this.date_begin = date_end;
    }
}
