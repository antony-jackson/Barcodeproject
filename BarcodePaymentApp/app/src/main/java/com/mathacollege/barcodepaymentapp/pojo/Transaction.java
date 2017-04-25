package com.mathacollege.barcodepaymentapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Antony on 4/1/2017.
 */

public class Transaction {

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("productname")
    @Expose
    private String productname;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("Actualprice")
    @Expose
    private String actualprice;
    @SerializedName("Qty")
    @Expose
    private String qty;

    public Transaction() {
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getActualprice() {
        return actualprice;
    }

    public void setActualprice(String actualprice) {
        this.actualprice = actualprice;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
