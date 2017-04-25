package com.mathacollege.barcodepaymentapp.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.mathacollege.barcodepaymentapp.R;
import com.mathacollege.barcodepaymentapp.fragments.ViewproductFragment;

public class MainActivity extends AppCompatActivity {


     LinearLayout container;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainlaunch);


        container=(LinearLayout) findViewById(R.id.container);

        addFragment( ViewproductFragment.newInstance());






    }







    public void addFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment).setTransition(FragmentTransaction.TRANSIT_NONE).commit();
    }



}
