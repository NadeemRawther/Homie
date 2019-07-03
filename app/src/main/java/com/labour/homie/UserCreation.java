package com.labour.homie;

public class UserCreation {
    private String name,userid,password;

    public UserCreation(String name, String userid, String password) {
        this.name = name;
        this.userid = userid;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getUserid() {
        return userid;
    }

    public String getPassword() {
        return password;
    }
}
