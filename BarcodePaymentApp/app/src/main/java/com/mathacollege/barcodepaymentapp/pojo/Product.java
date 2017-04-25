package com.mathacollege.barcodepaymentapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Antony on 3/2/2017.
 */

public class Product implements Serializable {

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("productname")
    @Expose
    private String productname;
    @SerializedName("vendername")
    @Expose
    private String vendername;
    @SerializedName("barcodenumber")
    @Expose
    private String barcodenumber;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("offer")
    @Expose
    private String offer;
    @SerializedName("actualprice")
    @Expose
    private String actualprice;

    @SerializedName("stock")
    @Expose
    private String stock;



    public Product() {
    }


    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getVendername() {
        return vendername;
    }

    public void setVendername(String vendername) {
        this.vendername = vendername;
    }

    public String getBarcodenumber() {
        return barcodenumber;
    }

    public void setBarcodenumber(String barcodenumber) {
        this.barcodenumber = barcodenumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getActualprice() {
        return actualprice;
    }

    public void setActualprice(String actualprice) {
        this.actualprice = actualprice;
    }
}
