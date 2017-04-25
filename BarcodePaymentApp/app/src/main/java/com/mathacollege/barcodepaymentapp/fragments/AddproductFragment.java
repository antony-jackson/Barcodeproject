package com.mathacollege.barcodepaymentapp.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.mathacollege.barcodepaymentapp.R;



public class AddproductFragment extends Fragment {


    View view;

    EditText editText_productname,editText_vendor,editText_price,editText_offer,editText_actualprice,editText_barcode;

    ImageButton imageButton_camera;

    Button button_add;

    Context context;

    public AddproductFragment() {
        // Required empty public constructor
    }



    public static AddproductFragment newInstance() {
        AddproductFragment fragment = new AddproductFragment();
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
        view= inflater.inflate(R.layout.fragment_addproduct, container, false);

context=getActivity();

        editText_productname=(EditText) view.findViewById(R.id.editText_productname);

        editText_vendor=(EditText) view.findViewById(R.id.editText_vendor);
        editText_price=(EditText) view.findViewById(R.id.editText_price);
        editText_offer=(EditText) view.findViewById(R.id.editText_offer);
        editText_actualprice=(EditText) view.findViewById(R.id.editText_actualprice);
        editText_barcode=(EditText) view.findViewById(R.id.editText_barcode);


        imageButton_camera=(ImageButton) view.findViewById(R.id.imageButton_camera);
        button_add=(Button) view.findViewById(R.id.button_add);



        imageButton_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IntentIntegrator integrator = IntentIntegrator.forFragment(AddproductFragment.newInstance());
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
                integrator.setPrompt("Scan a barcode");
                integrator.setCameraId(0);  // Use a specific camera of the device
                integrator.setBeepEnabled(false);
//                integrator.set(true);
                integrator.initiateScan();
            }
        });












        return view;
    }






//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if(result != null) {
//            if(result.getContents() == null) {
//                Log.d("MainActivity", "Cancelled scan");
//                Toast.makeText(context, "Cancelled", Toast.LENGTH_LONG).show();
//            } else {
//
//                // Toast.makeText(this, , Toast.LENGTH_LONG).show();
//
//                Toast.makeText(context, result.getContents(), Toast.LENGTH_LONG).show();
//
//
//               // editText_barcode.setText("Scanned: " + result.getContents());
//            }
//        } else {
//            // This is important, otherwise the result will not be passed to the fragment
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//    }

}
