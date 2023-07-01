package com.petcare.Model;

public class Type {
    private int ID;
    private String name;
    private String info;

    public Type() {
    }
    public Type(int ID, String name, String info) {
        this.ID = ID;
        this.name = name;
        this.info = info;
    }

    public Type(String name, String info) {
        this.name = name;
        this.info = info;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    public String toString() {
        return this.name;
    }
}
