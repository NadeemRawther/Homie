package com.labour.homie.Entities;

public class CardForLabours {
private String name,phone,place,charge,userid;


    public CardForLabours(String name, String phone, String place, String charge,String userid  ) {
        this.name = name;
        this.phone = phone;
        this.place = place;
        this.charge = charge;
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPlace() {
        return place;
    }

    public String getCharge() {
        return charge;
    }
}
