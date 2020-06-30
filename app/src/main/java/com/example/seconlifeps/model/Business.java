package com.example.seconlifeps.model;

import java.io.Serializable;

public class Business implements Serializable {
    private String bu_address;
    private String bu_visitDays;
    private String bu_visitHours;
    private String bu_contactNo;
    private String bu_email;
    private String bu_price;
    private int    bu_imagenId;


   // sql = " CREATE TABLE " + TBName_Business + "(bu_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
   //         "bu_name VARCHAR, bu_address VARCHAR, bu_lastName VARCHAR, bu_latitude VARCHAR, bu_longitude VARCHAR, bu_contactNo VARCHAR,\n" +
   //         "bu_email VARCHAR,\n" + "bu_description,\n" + "bu_price DECIMAL , \n" + "bu_visitDays VARCHAR,\n" + "bu_vi



    public Business(String bu_address, String bu_visitDays, String bu_visitHours, String bu_contactNo, String bu_email, String bu_price, int bu_imagenId) {
        this.bu_address = bu_address;
        this.bu_visitDays = bu_visitDays;
        this.bu_visitHours = bu_visitHours;
        this.bu_contactNo = bu_contactNo;
        this.bu_email = bu_email;
        this.bu_price = bu_price;
        this.bu_imagenId = bu_imagenId;
    }

    public Business() {

    }

    public String getBu_address() {
        return bu_address;
    }

    public void setBu_address(String bu_address) {
        this.bu_address = bu_address;
    }

    public String getBu_visitDays() {
        return bu_visitDays;
    }

    public void setBu_visitDays(String bu_visitDays) {
        this.bu_visitDays = bu_visitDays;
    }

    public String getBu_visitHours() {
        return bu_visitHours;
    }

    public void setBu_visitHours(String bu_visitHours) {
        this.bu_visitHours = bu_visitHours;
    }

    public String getBu_contactNo() {
        return bu_contactNo;
    }

    public void setBu_contactNo(String bu_contactNo) {
        this.bu_contactNo = bu_contactNo;
    }

    public String getBu_email() {
        return bu_email;
    }

    public void setBu_email(String bu_email) {
        this.bu_email = bu_email;
    }

    public String getBu_price() {
        return bu_price;
    }

    public void setBu_price(String bu_price) {
        this.bu_price = bu_price;
    }

    public int getBu_imagenId() {
        return bu_imagenId;
    }

    public void setBu_imagenId(int bu_imagenId) {
        this.bu_imagenId = bu_imagenId;
    }
}
