package com.mathacollege.barcodepaymentapp.fragments;



import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mathacollege.barcodepaymentapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewproductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewproductFragment extends Fragment {


    View view;


    public ViewproductFragment() {
        // Required empty public constructor
    }


    public static ViewproductFragment newInstance() {
        ViewproductFragment fragment = new ViewproductFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_viewproduct, container, false);

        return view;
    }











}
