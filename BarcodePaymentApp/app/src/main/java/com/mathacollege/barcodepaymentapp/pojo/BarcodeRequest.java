package com.mathacollege.barcodepaymentapp.pojo;

/**
 * Created by Antony on 3/2/2017.
 */

public class BarcodeRequest {

    String barcodenumber;

    public BarcodeRequest() {
    }

    public BarcodeRequest(String barcodenumber) {
        this.barcodenumber = barcodenumber;
    }

    public String getBarcodenumber() {
        return barcodenumber;
    }

    public void setBarcodenumber(String barcodenumber) {
        this.barcodenumber = barcodenumber;
    }
}
