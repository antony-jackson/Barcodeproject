package com.mathacollege.barcodepaymentapp.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mathacollege.barcodepaymentapp.R;
import com.mathacollege.barcodepaymentapp.variables.Prefers;
import com.mathacollege.barcodepaymentapp.variables.Utilities;

public class SplashActivity extends AppCompatActivity {


    Thread thread;

    Context context;

    Prefers prefers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        context=SplashActivity.this;

        prefers=new Prefers(context);

        getSupportActionBar().hide();

        thread=new Thread(new Runnable() {
            @Override
            public void run() {


                try{




                    thread.sleep(3000);

                    if(prefers.getString(Utilities.userinstaceid).equals("")) {


                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        finish();
                    }
                    else {

                        if(prefers.getboolean(Utilities.adminkey))
                        {
                            startActivity(new Intent(SplashActivity.this, MainAdminActivity.class));
                        }
                        else {

                            startActivity(new Intent(SplashActivity.this, Main_ClientActivity.class));
                        }





                        finish();
                    }



                }catch (Exception e)
                {

                }



            }
        });

        thread.start();
    }
}
