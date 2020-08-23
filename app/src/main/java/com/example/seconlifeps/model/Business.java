package com.example.seconlifeps.model;

import java.io.Serializable;

public class Business implements Serializable {
    private Integer bu_id;
    private String bu_name;
    private String bu_address;
    private String bu_visitDays;
    private String bu_visitHours;
    private String bu_contactNo;
    private String bu_email;
    private String bu_price;
    private int    bu_imagenId;
    private Double bu_lat;
    private Double bu_lon;

    public Business(Integer bu_id, String bu_name, String bu_address, String bu_visitDays, String bu_visitHours, String bu_contactNo, String bu_email, String bu_price, int bu_imagenId, Double bu_lat, Double bu_lon) {
        this.bu_id = bu_id;
        this.bu_name = bu_name;
        this.bu_address = bu_address;
        this.bu_visitDays = bu_visitDays;
        this.bu_visitHours = bu_visitHours;
        this.bu_contactNo = bu_contactNo;
        this.bu_email = bu_email;
        this.bu_price = bu_price;
        this.bu_imagenId = bu_imagenId;
        this.bu_lat = bu_lat;
        this.bu_lon = bu_lon;
    }

    public Business() {

    }

    public Integer getBu_id() {
        return bu_id;
    }

    public void setBu_id(Integer bu_id) {
        this.bu_id = bu_id;
    }

    public String getBu_name() {
        return bu_name;
    }

    public void setBu_name(String bu_name) {
        this.bu_name = bu_name;
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

    public Double getBu_lat() {
        return bu_lat;
    }

    public void setBu_lat(Double bu_lat) {
        this.bu_lat = bu_lat;
    }

    public Double getBu_lon() {
        return bu_lon;
    }

    public void setBu_lon(Double bu_lon) {
        this.bu_lon = bu_lon;
    }
}
