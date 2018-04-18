package com.example.bnm20.myapplication;

public class Weather {
    private String baseData;
    private String baseTime;
    private String category;
    private String fcstDate;
    private String fcstTime;
    private double fcstValue;
    private int nx;
    private int ny;


    public String getBaseData() {
        return baseData;
    }

    public void setBaseData(String baseData) {
        this.baseData = baseData;
    }

    public String getBaseTime() {
        return baseTime;
    }

    public void setBaseTime(String baseTime) {
        this.baseTime = baseTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFcstDate() {
        return fcstDate;
    }

    public void setFcstDate(String fcstDate) {
        this.fcstDate = fcstDate;
    }

    public String getFcstTime() {
        return fcstTime;
    }

    public void setFcstTime(String fcstTime) {
        this.fcstTime = fcstTime;
    }

    public double getFcstValue() {
        return fcstValue;
    }

    public void setFcstValue(double fcstValue) {
        this.fcstValue = fcstValue;
    }

    public int getNx() {
        return nx;
    }

    public void setNx(int nx) {
        this.nx = nx;
    }

    public int getNy() {
        return ny;
    }

    public void setNy(int ny) {
        this.ny = ny;
    }

}
