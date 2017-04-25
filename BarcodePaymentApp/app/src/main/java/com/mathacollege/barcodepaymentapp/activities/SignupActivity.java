package com.mathacollege.barcodepaymentapp.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mathacollege.barcodepaymentapp.R;
import com.mathacollege.barcodepaymentapp.database.Databasehelper;
import com.mathacollege.barcodepaymentapp.pojo.User;
import com.mathacollege.barcodepaymentapp.variables.Prefers;
import com.mathacollege.barcodepaymentapp.variables.Utilities;
import com.mathacollege.barcodepaymentapp.webinterfaces.CallInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignupActivity extends AppCompatActivity {

    Button button_Signup, button_login;

    EditText editText_password, editText_email, editText_confirmpassword, editText_firstname, editText_lastname;


    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().hide();

        context = SignupActivity.this;

        button_Signup = (Button) this.findViewById(R.id.button_Signup);
        button_login = (Button) this.findViewById(R.id.button_login);


        editText_password = (EditText) this.findViewById(R.id.editText_password);
        editText_email = (EditText) this.findViewById(R.id.editText_email);
        editText_confirmpassword = (EditText) this.findViewById(R.id.editText_confirmpassword);

        editText_firstname = (EditText) this.findViewById(R.id.editText_firstname);

        editText_lastname = (EditText) this.findViewById(R.id.editText_lastname);


        button_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!editText_firstname.getText().toString().equals(""))

                {


                    if (!editText_lastname.getText().toString().equals(""))

                    {


                        if (Utilities.emailValidator(editText_email.getText().toString())) {

                            if (!editText_password.getText().toString().equals("")) {

                                if (editText_password.getText().toString().trim().length() >= 6) {

                                    if (editText_password.getText().toString().equals(editText_confirmpassword.getText().toString())) {


                                        final ProgressDialog progressDialog=new ProgressDialog(context);
                                        progressDialog.setMessage("loading.....");
                                        progressDialog.setCancelable(false);
                                        progressDialog.show();


                                        final String  id=Utilities.getInstanceid();
                                        User user=new User();
                                        user.setFirstname(editText_firstname.getText().toString());
                                        user.setLastname(editText_lastname.getText().toString());

                                        user.setEmail(editText_email.getText().toString());
                                        user.setPassword(editText_password.getText().toString());
                                        user.setInstanceid(id);


                                        Retrofit retrofit=Utilities.getRetrofitApi();

                                        Call<String>stringCall=retrofit.create(CallInterface.class).STRING_CALL_Signup(user);
                                        stringCall.enqueue(new Callback<String>() {
                                            @Override
                                            public void onResponse(Call<String> call, Response<String> response) {

                                                progressDialog.dismiss();

                                                if(response.code()==400)
                                                {
                                                    Toast.makeText(context,"Email Already exists",Toast.LENGTH_SHORT).show();
                                                }
                                                else if(response.code()==200)
                                                {
                                                    new Prefers(context).putString(Utilities.userinstaceid,id);

//                                                    startActivity(new Intent(SignupActivity.this,MainActivity.class));
//                                                    finish();

                                                    login_as_user();

                                                }





                                            }

                                            @Override
                                            public void onFailure(Call<String> call, Throwable t) {

                                                progressDialog.dismiss();
                                                Toast.makeText(context,t.toString(),Toast.LENGTH_SHORT).show();


                                            }
                                        });























                                    } else {
                                        Utilities.showAlert(context, "Password Confirmation failed");
                                    }


                                } else {

                                    Utilities.showAlert(context, "weak password,must have atleast 6 characters");
                                }


                            } else {


                                Utilities.showAlert(context, "Invalid password");
                            }


                        } else {


                            Utilities.showAlert(context, "Invalid email");
                        }


                    } else {
                        Utilities.showAlert(context, "you must enter last name");
                    }


                } else {


                    Utilities.showAlert(context, "you must enter first name");
                }


            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();

            }
        });

    }




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

                    new Prefers(context).putBoolean(Utilities.adminkey, false);
                    new Databasehelper(context).add_user(user);
//
                    startActivity(new Intent(SignupActivity.this, Main_ClientActivity.class));
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
