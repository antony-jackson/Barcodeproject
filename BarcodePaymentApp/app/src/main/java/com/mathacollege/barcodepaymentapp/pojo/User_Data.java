package com.mathacollege.barcodepaymentapp.pojo;

/**
 * Created by Antony on 3/6/2017.
 */

public class User_Data {

    String username;
    String password;

    public User_Data(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
