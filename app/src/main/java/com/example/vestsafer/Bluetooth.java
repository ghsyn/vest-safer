package com.example.vestsafer;

public class Bluetooth {
    String userID;
    String str_btn;
    String str_dust;
    String str_co;
    String str_hum;
    String str_temp;

    public Bluetooth(String userID, String str_btn, String str_dust, String str_co, String str_hum, String str_temp) {

        this.userID=userID;
        this.str_btn=str_btn;
        this.str_dust=str_dust;
        this.str_co=str_co;
        this.str_hum=str_hum;
        this.str_temp=str_temp;
    }

    public String getUserID() { return userID; }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getStr_btn() {
        return str_btn;
    }

    public void setStr_btn(String str_btn) {
        this.str_btn = str_btn;
    }


    public String getStr_dust() {
        return str_dust;
    }

    public void setStr_dust(String str_dust) {
        this.str_dust = str_dust;
    }
    public String getStr_co() {
        return str_co;
    }

    public void setStr_co(String str_co) {
        this.str_co = str_co;
    }
    public String getStr_hum() {
        return str_hum;
    }

    public void setStr_hum(String str_hum) {
        this.str_hum = str_hum;
    }
    public String getStr_temp() {
        return str_temp;
    }

    public void setStr_temp(String str_temp) {
        this.str_co = str_co;
    }





}
