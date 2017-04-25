package com.mathacollege.barcodepaymentapp.pojo;

/**
 * Created by Antony on 2/23/2017.
 */

public class User {

    String Firstname;
    String Lastname;
    String email;
    String password;
    String instanceid;

    public User(String firstname, String lastname, String email, String password, String instanceid) {
        Firstname = firstname;
        Lastname = lastname;
        this.email = email;
        this.password = password;
        this.instanceid = instanceid;
    }

    public User() {
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInstanceid() {
        return instanceid;
    }

    public void setInstanceid(String instanceid) {
        this.instanceid = instanceid;
    }
}
