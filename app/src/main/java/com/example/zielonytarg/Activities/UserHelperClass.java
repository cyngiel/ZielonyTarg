package com.example.zielonytarg.Activities;

public class UserHelperClass {
    String name, emial, password;

    public UserHelperClass() {
    }

    public UserHelperClass(String name, String emial, String password) {
        this.name = name;
        this.emial = emial;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmial() {
        return emial;
    }

    public void setEmial(String emial) {
        this.emial = emial;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
