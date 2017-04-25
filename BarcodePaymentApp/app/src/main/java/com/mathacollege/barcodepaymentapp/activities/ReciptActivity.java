package com.mathacollege.barcodepaymentapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.mathacollege.barcodepaymentapp.R;
import com.mathacollege.barcodepaymentapp.adapter.Recycler_Selectedproductadapter;
import com.mathacollege.barcodepaymentapp.database.Databasehelper;
import com.mathacollege.barcodepaymentapp.pojo.Recipt;
import com.mathacollege.barcodepaymentapp.pojo.User;

public class ReciptActivity extends AppCompatActivity {

    TextView textView_user;

    RecyclerView recycler_product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipt);

        textView_user=(TextView) this.findViewById(R.id.textView_user);

        recycler_product=(RecyclerView) this.findViewById(R.id.rec);

        User user=new Databasehelper(ReciptActivity.this).get_User();

        textView_user.setText(user.getEmail());

        Recipt recipt=(Recipt) getIntent().getSerializableExtra("Recipt");

        recycler_product.setLayoutManager(new LinearLayoutManager(ReciptActivity.this));
        Recycler_Selectedproductadapter  recycler_selectedproductadapter=new Recycler_Selectedproductadapter(ReciptActivity.this, recipt.getProduct());
        recycler_product.setAdapter(recycler_selectedproductadapter);

    }
}
