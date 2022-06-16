package com.example.smartvest;

public class UserLocation {
    String userID;
    String str_datetime;
    double str_latitude;
    double str_longitude;
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getStr_datetime() {
        return str_datetime;
    }

    public void setStr_datetime(String str_datetime) {
        this.str_datetime = str_datetime;
    }

    public Double getStr_latitude() { return str_latitude; }

    public void setStr_latitude(Double str_latitude) {
        this.str_latitude = str_latitude;
    }

    public Double getStr_longitude() {
        return str_longitude;
    }

    public void setStr_longitude(Double str_longitude) {
        this.str_longitude = str_longitude;
    }

    public UserLocation(String userID, String str_datetime, Double str_latitude, Double str_longitude) {
        this.userID = userID;
        this.str_datetime = str_datetime;
        this.str_latitude = str_latitude;
        this.str_longitude = str_longitude;
    }
}
