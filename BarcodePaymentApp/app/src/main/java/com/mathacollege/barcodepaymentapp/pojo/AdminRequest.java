package com.mathacollege.barcodepaymentapp.pojo;

/**
 * Created by Antony on 2/23/2017.
 */

public class AdminRequest {

    String email;
    String password;
    String instanceid;
    String sha1fingerprintkey;

    public AdminRequest(String email, String password, String instanceid, String sha1fingerprintkey) {
        this.email = email;
        this.password = password;
        this.instanceid = instanceid;
        this.sha1fingerprintkey = sha1fingerprintkey;
    }

    public AdminRequest() {
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

    public String getSha1fingerprintkey() {
        return sha1fingerprintkey;
    }

    public void setSha1fingerprintkey(String sha1fingerprintkey) {
        this.sha1fingerprintkey = sha1fingerprintkey;
    }
}
