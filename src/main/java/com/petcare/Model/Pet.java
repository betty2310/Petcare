package com.petcare.Model;

public class Pet {
    private int ID;
    private String name;
    private char gender;
    private String info;
    private int ownerID;
    private int employeeID;

    public Pet() {
    }

    public Pet(int ID, String name, char gender, String info, int ownerID, int employeeID) {
        this.ID = ID;
        this.name = name;
        this.gender = gender;
        this.info = info;
        this.ownerID = ownerID;
        this.employeeID = employeeID;
    }

    public Pet(String name, char gender, String info, int ownerID, int employeeID) {
        this.name = name;
        this.gender = gender;
        this.info = info;
        this.ownerID = ownerID;
        this.employeeID = employeeID;
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

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
}
