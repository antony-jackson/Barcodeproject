package com.mathacollege.barcodepaymentapp.pojo;

/**
 * Created by Antony on 3/6/2017.
 */

public class Transactionlist {


    int id;
    String email;
    String productname;
    double price;
    double Actualprice;
    int Qty;
    int stock;

    public Transactionlist(String email, String productname, double price, double actualprice, int qty) {
        this.email = email;
        this.productname = productname;
        this.price = price;
        Actualprice = actualprice;
        this.Qty = qty;
    }

    public Transactionlist() {
    }


    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getActualprice() {
        return Actualprice;
    }

    public void setActualprice(double actualprice) {
        Actualprice = actualprice;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        this.Qty = qty;
    }
}
