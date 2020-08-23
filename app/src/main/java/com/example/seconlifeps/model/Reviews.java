package com.example.seconlifeps.model;

import java.io.Serializable;

public class Reviews implements Serializable {
    private Integer rv_id;
    private Integer bu_id;
    private Integer us_id;
    private String rv_date;
    private String rv_description;

    public Reviews(Integer rv_id, Integer bu_id, Integer us_id, String rv_date, String rv_description) {
        this.rv_id = rv_id;
        this.bu_id = bu_id;
        this.us_id = us_id;
        this.rv_date = rv_date;
        this.rv_description = rv_description;
    }

    public Reviews() {

    }

    public Integer getRv_id() {
        return rv_id;
    }

    public void setRv_id(Integer rv_id) {
        this.rv_id = rv_id;
    }

    public Integer getBu_id() {
        return bu_id;
    }

    public void setBu_id(Integer bu_id) {
        this.bu_id = bu_id;
    }

    public Integer getUs_id() {
        return us_id;
    }

    public void setUs_id(Integer us_id) {
        this.us_id = us_id;
    }

    public String getRv_date() {
        return rv_date;
    }

    public void setRv_date(String rv_date) {
        this.rv_date = rv_date;
    }

    public String getRv_description() {
        return rv_description;
    }

    public void setRv_description(String rv_description) {
        this.rv_description = rv_description;
    }
}
