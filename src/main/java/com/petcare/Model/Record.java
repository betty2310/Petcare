package com.petcare.Model;


public class Record {
    private int ID;
    private  String status;
    private String medication;

    private int petID;

    public Record(int ID, String status, String medication, int petID) {
        this.ID = ID;
        this.status = status;
        this.medication = medication;
        this.petID = petID;
    }

    public Record(){

    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public int getPetID() {
        return petID;
    }

    public void setPetID(int petID) {
        this.petID = petID;
    }
}
