package com.example.seconlifeps.model;

import java.io.Serializable;

public class User implements Serializable {

    private Integer us_id;
    private String  us_email;
    private String us_password;
    private String us_firstName;
    private String us_lastName;
    private String us_dob; //DATE
    private byte[] us_image; // BLOB
    private String us_latitude; // VARCHAR,
    private String us_longitude; //VARCHAR,
    private String us_lastLogin; // DATE

    public User() {}

    public User(Integer us_id, String us_email, String us_password, String us_firstName, String us_lastName, String us_dob, byte[] us_image, String us_latitude, String us_longitude, String us_lastLogin) {
        this.us_id = us_id;
        this.us_email = us_email;
        this.us_password = us_password;
        this.us_firstName = us_firstName;
        this.us_lastName = us_lastName;
        this.us_dob = us_dob;
        this.us_image = us_image;
        this.us_latitude = us_latitude;
        this.us_longitude = us_longitude;
        this.us_lastLogin = us_lastLogin;
    }

    public Integer getUs_id() {
        return us_id;
    }

    public void setUs_id(Integer us_id) {
        this.us_id = us_id;
    }

    public String getUs_email() {
        return us_email;
    }

    public void setUs_email(String us_email) {
        this.us_email = us_email;
    }

    public String getUs_password() {
        return us_password;
    }

    public void setUs_password(String us_password) {
        this.us_password = us_password;
    }

    public String getUs_firstName() {
        return us_firstName;
    }

    public void setUs_firstName(String us_firstName) {
        this.us_firstName = us_firstName;
    }

    public String getUs_lastName() {
        return us_lastName;
    }

    public void setUs_lastName(String us_lastName) {
        this.us_lastName = us_lastName;
    }

    public String getUs_dob() {
        return us_dob;
    }

    public void setUs_dob(String us_dob) {
        this.us_dob = us_dob;
    }

    public byte[] getUs_image() {
        return us_image;
    }

    public void setUs_image(byte[] us_image) {
        this.us_image = us_image;
    }

    public String getUs_latitude() {
        return us_latitude;
    }

    public void setUs_latitude(String us_latitude) {
        this.us_latitude = us_latitude;
    }

    public String getUs_longitude() {
        return us_longitude;
    }

    public void setUs_longitude(String us_longitude) {
        this.us_longitude = us_longitude;
    }

    public String getUs_lastLogin() {
        return us_lastLogin;
    }

    public void setUs_lastLogin(String us_lastLogin) {
        this.us_lastLogin = us_lastLogin;
    }
}
