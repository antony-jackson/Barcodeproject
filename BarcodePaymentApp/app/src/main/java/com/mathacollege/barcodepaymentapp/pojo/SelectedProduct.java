package com.mathacollege.barcodepaymentapp.pojo;

import java.io.Serializable;

/**
 * Created by Antony on 3/3/2017.
 */

public class SelectedProduct implements Serializable {

    String productinfo;

    double product_price;

    Product product;
    int qty;

    public SelectedProduct(String productinfo, double product_price, Product product) {
        this.productinfo = productinfo;
        this.product_price = product_price;
        this.product = product;
    }

    public SelectedProduct() {
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getProductinfo() {
        return productinfo;
    }

    public void setProductinfo(String productinfo) {
        this.productinfo = productinfo;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }
}
