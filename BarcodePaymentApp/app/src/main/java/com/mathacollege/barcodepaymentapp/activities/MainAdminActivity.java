package com.mathacollege.barcodepaymentapp.activities;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.mathacollege.barcodepaymentapp.R;
import com.mathacollege.barcodepaymentapp.fragments.AddproductFragment;
import com.mathacollege.barcodepaymentapp.fragments.ViewproductFragment;
import com.mathacollege.barcodepaymentapp.pojo.ProductRequest;
import com.mathacollege.barcodepaymentapp.variables.Prefers;
import com.mathacollege.barcodepaymentapp.variables.Utilities;
import com.mathacollege.barcodepaymentapp.webinterfaces.CallInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainAdminActivity extends AppCompatActivity {


    EditText editText_productname, editText_vendor, editText_price, editText_offer, editText_actualprice, editText_barcode;

    ImageButton imageButton_camera,imageButton_logout,imageButton_view;

    Button button_add;

    Context context;


    double price = 0, actual_price = 0, offer = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        context = MainAdminActivity.this;


        editText_productname = (EditText) this.findViewById(R.id.editText_productname);

        editText_vendor = (EditText) this.findViewById(R.id.editText_vendor);
        editText_price = (EditText) this.findViewById(R.id.editText_price);
        editText_offer = (EditText) this.findViewById(R.id.editText_offer);
        editText_actualprice = (EditText) this.findViewById(R.id.editText_actualprice);
        editText_barcode = (EditText) this.findViewById(R.id.editText_barcode);




        imageButton_camera = (ImageButton) this.findViewById(R.id.imageButton_camera);
        imageButton_view= (ImageButton) this.findViewById(R.id.imageButton_view);

        imageButton_logout= (ImageButton) this.findViewById(R.id.imageButton_logout);
        button_add = (Button) this.findViewById(R.id.button_add);


        imageButton_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAdminActivity.this,ListTransactions_Activity.class));

            }
        });





        imageButton_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Prefers(context).putString(Utilities.userinstaceid, "");

                new Prefers(context).putBoolean(Utilities.adminkey, false);

                startActivity(new Intent(MainAdminActivity.this,LoginActivity.class));
                finish();
            }
        });


        imageButton_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IntentIntegrator integrator = new IntentIntegrator(MainAdminActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
                integrator.setPrompt("Scan a barcode");
                integrator.setCameraId(0);  // Use a specific camera of the device
                integrator.setBeepEnabled(false);
//                integrator.set(true);
                integrator.initiateScan();
            }
        });


        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editText_productname.getText().toString().equals("")) {


                    if (!editText_price.getText().toString().equals("")) {

                        if (!editText_barcode.getText().toString().equals("")) {


                            ProductRequest productRequest = new ProductRequest();
                            productRequest.setProductname(editText_productname.getText().toString());
                            productRequest.setVendername(editText_vendor.getText().toString());
                            productRequest.setPrice(String.valueOf(price));
                            productRequest.setOffer(String.valueOf(offer));
                            productRequest.setActualprice(String.valueOf(actual_price));
                            productRequest.setBarcodenumber(editText_barcode.getText().toString());


                            final ProgressDialog progressDialog=new ProgressDialog(context);
                            progressDialog.setMessage("loading.....");
                            progressDialog.setCancelable(false);
                            progressDialog.show();


                            Retrofit retrofit = Utilities.getRetrofitApi();

                            Call<String> stringCall = retrofit.create(CallInterface.class).STRING_CALL_addproduct(productRequest);

                            stringCall.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {

                                    progressDialog.dismiss();

                                    if(response.code()==400)
                                    {


                                        Toast.makeText(context,"Product already added ",Toast.LENGTH_SHORT).show();



                                    }
                                    else if(response.code()==200){

                                        Toast.makeText(context,"Product added successfully ",Toast.LENGTH_SHORT).show();

                                        editText_productname.setText("");
                                        editText_vendor.setText("");
                                         editText_price.setText("");
                                        editText_offer.setText("");
                                        editText_actualprice.setText("");
                                        editText_barcode.setText("");
                                    }





                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {
                                    progressDialog.dismiss();
                                    Toast.makeText(context,t.toString(),Toast.LENGTH_SHORT).show();
                                }
                            });


                        } else {


                            Toast.makeText(context, "Enter Product barcode", Toast.LENGTH_SHORT).show();

                        }


                    } else {


                        Toast.makeText(context, "Enter Product price", Toast.LENGTH_SHORT).show();

                    }


                } else {

                    Toast.makeText(context, "Enter Product name", Toast.LENGTH_SHORT).show();
                }


            }
        });


        editText_offer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!editText_price.getText().toString().equals("")) {

                    price = Double.parseDouble(editText_price.getText().toString());
                }

                if (price != 0) {

                    if (!s.toString().equals("")) {

                        offer = Double.parseDouble(s.toString());

                        double price_offer = price * (offer / 100);

                        actual_price = price - price_offer;


                        editText_actualprice.setText(String.valueOf(actual_price));


                    } else {
                        editText_actualprice.setText(String.valueOf(price));
                    }


                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        editText_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!editText_offer.getText().toString().equals("")) {

                    offer = Double.parseDouble(editText_offer.getText().toString());
                }


                if (!s.toString().equals("")) {


                    price = Double.parseDouble(s.toString());

                    double price_offer = price * (offer / 100);

                    actual_price = price - price_offer;


                    editText_actualprice.setText(String.valueOf(actual_price));


                } else {
                    editText_actualprice.setText("0");
                    editText_price.setText("0");

                    editText_offer.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Log.d("MainActivity", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {

                // Toast.makeText(this, , Toast.LENGTH_LONG).show();
                editText_barcode.setText(result.getContents());
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }



}
