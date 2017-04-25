package com.mathacollege.barcodepaymentapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mathacollege.barcodepaymentapp.R;
import com.mathacollege.barcodepaymentapp.pojo.SelectedProduct;

import java.util.List;

/**
 * Created by Antony on 3/3/2017.
 */

public class Recycler_Selectedproductadapter extends RecyclerView.Adapter<Recycler_Selectedproductadapter.Recycler_Viewholder>{



    Context context;
    List<SelectedProduct>selectedProducts;


    public Recycler_Selectedproductadapter(Context context, List<SelectedProduct> selectedProducts) {
        this.context = context;
        this.selectedProducts = selectedProducts;
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
    public Recycler_Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(context);

        View view=layoutInflater.inflate(R.layout.layout_productlist,parent,false);
        return new Recycler_Viewholder(view);
    }

    @Override
    public void onBindViewHolder(Recycler_Viewholder holder, int position) {

        holder.textView.setText(selectedProducts.get(position).getProductinfo());

        holder.textView_price.setText(selectedProducts.get(position).getProduct_price()+" RS");



    }

    @Override
    public int getItemCount() {
        return selectedProducts.size();
    }
}
