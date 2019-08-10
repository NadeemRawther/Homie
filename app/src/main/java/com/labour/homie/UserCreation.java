package com.labour.homie;

public class UserCreation {
    private String name,password;

    public UserCreation(String name, String password) {
        this.name = name;

        this.password = password;
    }

    public String getName() {
        return name;
    }


    public String getPassword() {
        return password;
    }
}
