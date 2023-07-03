package com.petcare.Model;


public class Record {
    private int ID;
    private  String status;
    private String medication;

    private String pet_Name;

    public Record(int ID, String status, String medication, String pet_Name) {
        this.ID = ID;
        this.status = status;
        this.medication = medication;
        this.pet_Name = pet_Name;
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

    public String getPet_Name() {
        return pet_Name;
    }

    public void setPet_Name(String pet_Name) {
        this.pet_Name = pet_Name;
    }
}
