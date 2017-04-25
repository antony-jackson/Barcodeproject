package com.mathacollege.barcodepaymentapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mathacollege.barcodepaymentapp.pojo.User;

/**
 * Created by Antony on 3/6/2017.
 */

public class Databasehelper extends SQLiteOpenHelper {

    SQLiteDatabase sqLiteDatabase;

    public Databasehelper(Context context) {
        super(context, "barcodedb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String table_user="CREATE TABLE user ( Username TEXT , Password TEXT )";

        db.execSQL(table_user);



    }




    public void add_user(User user)
    {
        sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {


            ContentValues contentValues=new ContentValues();
            contentValues.put("Username",user.getEmail());
            contentValues.put("password",user.getPassword());

            sqLiteDatabase.insert("user",null,contentValues);


            sqLiteDatabase.setTransactionSuccessful();


        }catch (Exception e)
        {

        }
        finally {

            sqLiteDatabase.endTransaction();
            sqLiteDatabase.close();

        }
    }



    public User get_User()
    {
        sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        User user=new User();

        try {

            String query="SELECT * FROM user";

            Cursor cursor=sqLiteDatabase.rawQuery(query,null);

            while (cursor.moveToNext()) {

                user.setEmail(cursor.getString(0));
                user.setPassword(cursor.getString(1));

            }


            sqLiteDatabase.setTransactionSuccessful();


        }catch (Exception e)
        {

        }
        finally {

            sqLiteDatabase.endTransaction();
            sqLiteDatabase.close();

        }



        return user;


    }



    public void deleteUser()
    {
        sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        User user=new User();

        try {

            String query="DELETE  FROM user";

           sqLiteDatabase.execSQL(query);






            sqLiteDatabase.setTransactionSuccessful();


        }catch (Exception e)
        {

        }
        finally {

            sqLiteDatabase.endTransaction();
            sqLiteDatabase.close();

        }
    }








    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
