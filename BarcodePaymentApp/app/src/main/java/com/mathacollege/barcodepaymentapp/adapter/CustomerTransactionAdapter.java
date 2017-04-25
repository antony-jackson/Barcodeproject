package com.mathacollege.barcodepaymentapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mathacollege.barcodepaymentapp.R;
import com.mathacollege.barcodepaymentapp.pojo.SelectedProduct;
import com.mathacollege.barcodepaymentapp.pojo.Transaction;

import java.util.List;

/**
 * Created by Antony on 4/1/2017.
 */

public class CustomerTransactionAdapter extends RecyclerView.Adapter<CustomerTransactionAdapter.Recycler_Viewholder> {


    Context context;
    List<Transaction> transactions;


    public CustomerTransactionAdapter(Context context,  List<Transaction> transactions) {
        this.context = context;
        this.transactions = transactions;
    }
    public class Recycler_Viewholder extends RecyclerView.ViewHolder
    {
        TextView textView,textView_price;

        public Recycler_Viewholder(View itemView) {
            super(itemView);
            textView_price=(TextView) itemView.findViewById(R.id.textView_price);
            textView=(TextView) itemView.findViewById(R.id.textView);
        }
    }

    @Override
    public CustomerTransactionAdapter.Recycler_Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);

        View view=layoutInflater.inflate(R.layout.layout_productlist,parent,false);
        return new CustomerTransactionAdapter.Recycler_Viewholder(view);
    }

    @Override
    public void onBindViewHolder(CustomerTransactionAdapter.Recycler_Viewholder holder, int position) {

        holder.textView.setText(transactions.get(position).getEmail()+"\n,"+transactions.get(position).getProductname()+"\n Qty :"+transactions.get(position).getQty());

        holder.textView_price.setText(transactions.get(position).getActualprice()+" Rs");

    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }
}
