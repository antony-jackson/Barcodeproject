package com.mathacollege.barcodepaymentapp.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.mathacollege.barcodepaymentapp.R;
import com.mathacollege.barcodepaymentapp.adapter.CustomerTransactionAdapter;
import com.mathacollege.barcodepaymentapp.pojo.CustomerTransaction;
import com.mathacollege.barcodepaymentapp.pojo.Transaction;
import com.mathacollege.barcodepaymentapp.variables.Utilities;
import com.mathacollege.barcodepaymentapp.webinterfaces.CallInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListTransactions_Activity extends AppCompatActivity {

    RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_transactions_);
        recyclerview=(RecyclerView) this.findViewById(R.id.recyclerview);

        final ProgressDialog progressDialog=new ProgressDialog(ListTransactions_Activity.this);
        progressDialog.setMessage("Loading.....");
        progressDialog.setCancelable(false);
        progressDialog.show();



        Retrofit retrofit = Utilities.getRetrofitApi();

        Call<CustomerTransaction>customerTransactionCall=retrofit.create(CallInterface.class).CALL_Alltransaction();

        customerTransactionCall.enqueue(new Callback<CustomerTransaction>() {
            @Override
            public void onResponse(Call<CustomerTransaction> call, Response<CustomerTransaction> response) {
                progressDialog.dismiss();

                if(response.code()==200)
                {

                 List<Transaction>transactions= response.body().getTransaction();

                    CustomerTransactionAdapter customerTransactionAdapter=new CustomerTransactionAdapter(ListTransactions_Activity.this,transactions);

                    recyclerview.setLayoutManager(new LinearLayoutManager(ListTransactions_Activity.this));
                    recyclerview.setAdapter(customerTransactionAdapter);



                }
                else if(response.code()==404) {
                    Toast.makeText(ListTransactions_Activity.this,"Not found ",Toast.LENGTH_SHORT).show();
                }




            }

            @Override
            public void onFailure(Call<CustomerTransaction> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ListTransactions_Activity.this,t.toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
