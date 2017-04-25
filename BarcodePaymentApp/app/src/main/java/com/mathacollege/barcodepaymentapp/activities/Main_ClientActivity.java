package com.mathacollege.barcodepaymentapp.activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsAuthButton;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.mathacollege.barcodepaymentapp.R;
import com.mathacollege.barcodepaymentapp.adapter.Recycler_Selectedproductadapter;
import com.mathacollege.barcodepaymentapp.database.Databasehelper;
import com.mathacollege.barcodepaymentapp.fragments.ViewproductFragment;
import com.mathacollege.barcodepaymentapp.pojo.BarcodeRequest;
import com.mathacollege.barcodepaymentapp.pojo.Product;
import com.mathacollege.barcodepaymentapp.pojo.Productlist;
import com.mathacollege.barcodepaymentapp.pojo.Recipt;
import com.mathacollege.barcodepaymentapp.pojo.SelectedProduct;
import com.mathacollege.barcodepaymentapp.pojo.Transactionlist;
import com.mathacollege.barcodepaymentapp.pojo.User;
import com.mathacollege.barcodepaymentapp.variables.Prefers;
import com.mathacollege.barcodepaymentapp.variables.Utilities;
import com.mathacollege.barcodepaymentapp.webinterfaces.CallInterface;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Main_ClientActivity extends AppCompatActivity {


    RecyclerView recycler_product;

    ImageButton imgbtn_camera;

    TextView txt_totalprice;

    Context context;

    List<SelectedProduct> selectedProducts = new ArrayList<>();

    Button button_logout, button_buynow;

    Recycler_Selectedproductadapter recycler_selectedproductadapter;

    // DigitsAuthButton button_buynow;

    int event = 0;


    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "oByzcQfabGIoqhKRi9CL25Vem";
    private static final String TWITTER_SECRET = "ER6kLh2gCzL9njwCUmT9OUOJedICHFT4YdPCrUemQm5aZDfO1e";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__client);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits.Builder().build());

        recycler_product = (RecyclerView) findViewById(R.id.recycler_product);

        button_logout = (Button) findViewById(R.id.button_logout);

        button_buynow = (Button) findViewById(R.id.button_buynow);

        context = Main_ClientActivity.this;

        txt_totalprice = (TextView) findViewById(R.id.txt_totalprice);

        imgbtn_camera = (ImageButton) findViewById(R.id.imgbtn_camera);


        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Prefers(context).putString(Utilities.userinstaceid, "");
                new Databasehelper(context).deleteUser();

                startActivity(new Intent(Main_ClientActivity.this, LoginActivity.class));
                finish();
            }
        });


        imgbtn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                event = 1;
                IntentIntegrator integrator = new IntentIntegrator(Main_ClientActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
                integrator.setPrompt("Scan a barcode");
                integrator.setCameraId(0);  // Use a specific camera of the device
                integrator.setBeepEnabled(false);

//                integrator.set(true);
                integrator.initiateScan();
            }
        });


        button_buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (selectedProducts.size() > 0) {


                    final Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.layout_mobileverify);
                    dialog.setCancelable(false);
                    Button button = (Button) dialog.findViewById(R.id.button_cancel);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    DigitsAuthButton digitsAuthButton = (DigitsAuthButton) dialog.findViewById(R.id.button_usemobile);


                    Digits.clearActiveSession();

                    digitsAuthButton.setCallback(new AuthCallback() {
                        @Override
                        public void success(DigitsSession session, String phoneNumber) {
                            // TODO: associate the session userID with your user model
                            txt_totalprice.setText("");

                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Authentication successful for "
                                    + phoneNumber, Toast.LENGTH_LONG).show();


                            User user = new Databasehelper(context).get_User();


                            for (SelectedProduct selectedProduct : selectedProducts
                                    ) {

                                Product product = selectedProduct.getProduct();

                                Transactionlist transactionlist = new Transactionlist();
                                transactionlist.setId(Integer.parseInt(product.getiD()));
                                transactionlist.setQty(selectedProduct.getQty());
                                transactionlist.setActualprice(Double.parseDouble(product.getActualprice()));
                                transactionlist.setPrice(Double.parseDouble(product.getPrice()));
                                transactionlist.setEmail(user.getEmail());
                                transactionlist.setStock(Integer.parseInt(product.getStock()));
                                transactionlist.setProductname(selectedProduct.getProduct().getProductname());


                                Retrofit retrofit = Utilities.getRetrofitApi();

                                Call<String> stringCall = retrofit.create(CallInterface.class).STRING_CALL_addtransaction(transactionlist);
                                stringCall.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {

                                        Toast.makeText(context, response.body(), Toast.LENGTH_SHORT).show();
                                        selectedProducts.clear();

                                        if (recycler_selectedproductadapter != null) {
                                            recycler_selectedproductadapter.notifyDataSetChanged();
                                        }


                                    }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {

                                    }
                                });


                            }

                            Recipt recipt = new Recipt(selectedProducts);

                            startActivity(new Intent(Main_ClientActivity.this, ReciptActivity.class).putExtra("Recipt", recipt));


                        }

                        @Override
                        public void failure(DigitsException exception) {
                            Log.d("Digits", "Sign in with Digits failure", exception);
                        }
                    });


                    dialog.show();


                } else {


                    Toast.makeText(context, "No products scanned", Toast.LENGTH_SHORT).show();
                }


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

                if (event == 1) {


                    event = 0;
                    final ProgressDialog progressDialog = new ProgressDialog(context);
                    progressDialog.setMessage("loading.....");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    BarcodeRequest barcodeRequest = new BarcodeRequest();
                    barcodeRequest.setBarcodenumber(result.getContents());

                    Retrofit retrofit = Utilities.getRetrofitApi();

                    Call<Productlist> stringCall = retrofit.create(CallInterface.class).STRING_CALL_getproduct(barcodeRequest);

                    stringCall.enqueue(new Callback<Productlist>() {
                        @Override
                        public void onResponse(Call<Productlist> call, Response<Productlist> response) {
                            progressDialog.dismiss();

                            if (response.code() == 200) {

                                showProductdialog(response.body().getProduct());


                            } else if (response.code() == 404) {
                                Toast.makeText(context, "Product not found", Toast.LENGTH_SHORT).show();
                            }


                        }

                        @Override
                        public void onFailure(Call<Productlist> call, Throwable t) {
                            progressDialog.dismiss();

                            Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }

            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            // super.onActivityResult(requestCode, resultCode, data);
        }
    }


    public void showProductdialog(List<Product> productList) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.layout_productdialog);

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        final Product product = productList.get(0);

        TextView textView_details = (TextView) dialog.findViewById(R.id.textView_details);

        final EditText editText_qty = (EditText) dialog.findViewById(R.id.editText_qty);

        Button button_add = (Button) dialog.findViewById(R.id.button_add);

        Button button_cancel = (Button) dialog.findViewById(R.id.button_cancel);

        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(product.getProductname());
        stringBuilder.append("\n Price : " + product.getPrice() + " .Rs");

        stringBuilder.append("\n offer : " + product.getOffer() + "%");

        stringBuilder.append("\n Actual price : " + product.getActualprice() + " .Rs");

        textView_details.setText(stringBuilder.toString());


        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();


                if (!editText_qty.getText().toString().equals("")) {


                    int a = Integer.parseInt(editText_qty.getText().toString());

                    int currentstock = Integer.parseInt(product.getStock());

                    if (a != 0) {

                        if (currentstock >= a) {


                            double price = Double.parseDouble(product.getActualprice()) * a;

                            SelectedProduct selectedProduct = new SelectedProduct();
                            selectedProduct.setProductinfo(stringBuilder.toString());
                            selectedProduct.setProduct_price(price);
                            selectedProduct.setProduct(product);
                            selectedProduct.setQty(a);


                            selectedProducts.add(selectedProduct);

                            setRecycler_product(selectedProducts);
                            setSum(selectedProducts);

                        } else {

                            Toast.makeText(context, "out of stock", Toast.LENGTH_SHORT).show();

                        }


                    } else {

                        Toast.makeText(context, "Please enter quantity", Toast.LENGTH_SHORT).show();
                    }


                } else {

                    Toast.makeText(context, "Please enter quantity", Toast.LENGTH_SHORT).show();
                }


            }
        });


        dialog.show();


    }


    public void setRecycler_product(List<SelectedProduct> product) {

        recycler_product.setLayoutManager(new LinearLayoutManager(context));
        recycler_selectedproductadapter = new Recycler_Selectedproductadapter(context, product);
        recycler_product.setAdapter(recycler_selectedproductadapter);


    }


    public void setSum(List<SelectedProduct> product) {
        double sum = 0;

        for (SelectedProduct selectedProduct : product
                ) {

            sum = sum + selectedProduct.getProduct_price();
        }

        txt_totalprice.setText("Total price : " + sum + " RS");
    }


}
