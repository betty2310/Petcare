package com.petcare.Model;

import java.util.Date;

public class RevanueModel2 {
    private Date date_end;
    private String price;

    public RevanueModel2 (){

    }

    public RevanueModel2(Date date_end, String price) {
        this.date_end = date_end;
        this.price = price;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String total) {
        this.price = total;
    }
}
