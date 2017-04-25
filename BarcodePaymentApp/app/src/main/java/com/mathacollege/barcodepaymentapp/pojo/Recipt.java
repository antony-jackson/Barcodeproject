package com.mathacollege.barcodepaymentapp.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Antony on 4/2/2017.
 */

public class Recipt implements Serializable{
    List<SelectedProduct> product;

    public Recipt(List<SelectedProduct> product) {
        this.product = product;
    }

    public Recipt() {
    }

    public List<SelectedProduct> getProduct() {
        return product;
    }

    public void setProduct(List<SelectedProduct> product) {
        this.product = product;
    }
}
