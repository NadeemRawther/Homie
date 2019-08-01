package com.labour.homie;

public class LabourModel {

    String password;
    String name;
    String details;
    String place;
    String phone;
    String charge;
    String img;
    public LabourModel( String password, String name, String details, String place, String phone, String charge,String img) {
        this.password = password;
        this.name = name;
        this.details = details;
        this.place = place;
        this.phone = phone;
        this.charge = charge;
        this.img = img;
    }


    public String getImg() {
        return img;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public String getPlace() {
        return place;
    }

    public String getPhone() {
        return phone;
    }

    public String getCharge() {
        return charge;
    }
}
