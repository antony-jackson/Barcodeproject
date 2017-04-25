package com.mathacollege.barcodepaymentapp.webinterfaces;

import com.mathacollege.barcodepaymentapp.pojo.AdminRequest;
import com.mathacollege.barcodepaymentapp.pojo.BarcodeRequest;
import com.mathacollege.barcodepaymentapp.pojo.CustomerTransaction;
import com.mathacollege.barcodepaymentapp.pojo.ProductRequest;
import com.mathacollege.barcodepaymentapp.pojo.Productlist;
import com.mathacollege.barcodepaymentapp.pojo.Transactionlist;
import com.mathacollege.barcodepaymentapp.pojo.User;
import com.mathacollege.barcodepaymentapp.variables.UrlHelper;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Antony on 2/23/2017.
 */

public interface CallInterface {


    @POST(UrlHelper.adduserurl)
    Call<String>STRING_CALL_Signup(@Body User user);

    @POST(UrlHelper.loginurl)
    Call<String>STRING_CALL_login(@Body User user);


    @POST(UrlHelper.loginadminurl)
    Call<String>STRING_CALL_loginadmin(@Body AdminRequest adminRequest);



    @POST(UrlHelper.addproducturl)
    Call<String>STRING_CALL_addproduct(@Body ProductRequest productRequest);


    @POST(UrlHelper.getproducturl)
    Call<Productlist>STRING_CALL_getproduct(@Body BarcodeRequest barcodeRequest);


    @POST(UrlHelper.addtransaction)
    Call<String>STRING_CALL_addtransaction(@Body Transactionlist transactionlist);


    @POST(UrlHelper.getTransactionlist)
    Call<CustomerTransaction>CALL_Alltransaction();

}
