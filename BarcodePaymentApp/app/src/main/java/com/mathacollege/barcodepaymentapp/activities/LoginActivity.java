package com.mathacollege.barcodepaymentapp.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mathacollege.barcodepaymentapp.R;
import com.mathacollege.barcodepaymentapp.database.Databasehelper;
import com.mathacollege.barcodepaymentapp.pojo.AdminRequest;
import com.mathacollege.barcodepaymentapp.pojo.User;
import com.mathacollege.barcodepaymentapp.variables.Prefers;
import com.mathacollege.barcodepaymentapp.variables.Utilities;
import com.mathacollege.barcodepaymentapp.webinterfaces.CallInterface;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    Button button_signup, button_login;

    EditText editText_password, editText_email;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        context = LoginActivity.this;

        button_signup = (Button) this.findViewById(R.id.button_signup);
        button_login = (Button) this.findViewById(R.id.button_login);


        editText_password = (EditText) this.findViewById(R.id.editText_password);
        editText_email = (EditText) this.findViewById(R.id.editText_email);


        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                *
                * go to signup activity
                *
                * */

                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                finish();

            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    /*
                *
                * go to signup activity
                *
                * */

                if (!editText_email.getText().toString().equals(""))

                {


                    if (!editText_password.getText().toString().equals(""))

                    {

                        /*
                        * shows alert box
                        *
                        *
                        * */

//                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                        builder.setMessage("Do you want to login as administrator");
//                        builder.setCancelable(false);
//                        builder.setTitle("Qwikky");
//                        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();

                                /*
                                * call webservice for login administrator
                                *
                                * */
//                                login_as_administrator();
//
//                            }
//                        });
//
//
//                        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                                dialog.dismiss();

                                   /*
                                * call webservice for login user
                                *
                                * */
                                login_as_user();

//                            }
//                        });
//                        builder.show();





                    } else {

                        Utilities.showAlert(context, "please enter password");
                    }

                } else {

                    Utilities.showAlert(context, "please enter email");


                }

            }
        });


    }




//    public void login_as_administrator()
//    {
///*
//*
//* loading progress dialog
//*
//* */
//        final ProgressDialog progressDialog = new ProgressDialog(context);
//        progressDialog.setMessage("loading.....");
//        progressDialog.setCancelable(false);
//        progressDialog.show();
//
//        /*
//        *
//        * secure hash key using package name
//        *
//        * */
//
//        String SHa1key=Utilities.getSHA1(context);
//
//        AdminRequest adminRequest=new AdminRequest();
//        adminRequest.setEmail(editText_email.getText().toString());
//        adminRequest.setPassword(editText_password.getText().toString());
//        adminRequest.setSha1fingerprintkey(SHa1key);
//        final String id = Utilities.getInstanceid();
//        adminRequest.setInstanceid(id);
//
//
//        /*
//        *
//        * post request to server side
//        *
//        * */
//
//        Retrofit retrofit = Utilities.getRetrofitApi();
//
//        Call<String> stringCall = retrofit.create(CallInterface.class).STRING_CALL_loginadmin(adminRequest);
//        stringCall.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//
//                progressDialog.dismiss();
//
//                /*
//                *
//                * response from server
//                *
//                * */
//
//
//
//                if (response.code() == 401) {
//
//                         /*
//                    * if unauthorized ,server returns 401 status code
//                    *
//                    * */
//
//
//                    Toast.makeText(context, "Unauthorized user", Toast.LENGTH_SHORT).show();
//                } else if (response.code() == 200) {
//
//
//                    /*
//                    * if login successfully ,server returns 200 status code
//                    *
//                    * */
//
//
//                    new Prefers(context).putString(Utilities.userinstaceid, id);
//
//                    new Prefers(context).putBoolean(Utilities.adminkey, true);
//
////
//                    startActivity(new Intent(LoginActivity.this, MainAdminActivity.class));
//                    finish();
//
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                /*
//                *
//                * if server returns error
//                * */
//
//                progressDialog.dismiss();
//                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//
//
//
//
//
//
//
//    }


















    public void login_as_user()
    {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("loading.....");
        progressDialog.setCancelable(false);
        progressDialog.show();


        final String id = Utilities.getInstanceid();
        final User user = new User();


        user.setEmail(editText_email.getText().toString());
        user.setPassword(editText_password.getText().toString());
        user.setInstanceid(id);

        Retrofit retrofit = Utilities.getRetrofitApi();

        Call<String> stringCall = retrofit.create(CallInterface.class).STRING_CALL_login(user);

        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                progressDialog.dismiss();
                if (response.code() == 401) {
                    Toast.makeText(context, "Unauthorized user", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 200) {
                    new Prefers(context).putString(Utilities.userinstaceid, id);
                    new Databasehelper(context).add_user(user);

                    new Prefers(context).putBoolean(Utilities.adminkey, false);
//
                    startActivity(new Intent(LoginActivity.this, Main_ClientActivity.class));
                    finish();


                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
