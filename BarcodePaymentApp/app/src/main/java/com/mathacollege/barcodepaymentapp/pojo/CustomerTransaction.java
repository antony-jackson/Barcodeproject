package com.mathacollege.barcodepaymentapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Antony on 4/1/2017.
 */

public class CustomerTransaction {

    @SerializedName("Transaction")
    @Expose
    private List<Transaction> transaction = null;

    public List<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }

}
