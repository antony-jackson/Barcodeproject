package com.mathacollege.barcodepaymentapp.variables;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Antony on 12/4/2016.
 */

public class Prefers {

    Context context;
    SharedPreferences sharedPreferences;

    public Prefers(Context context) {
        this.context = context;
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);

    }


    public  void putBoolean(String key,boolean a)
    {
        sharedPreferences.edit().putBoolean(key,a).commit();
    }

    public boolean getboolean(String key)
    {
        return sharedPreferences.getBoolean(key,false);
    }





    public void putString(String key,String val)
    {
        sharedPreferences.edit().putString(key,val).commit();
    }




    public String getString(String key)
    {
       return sharedPreferences.getString(key,"");
    }

}
