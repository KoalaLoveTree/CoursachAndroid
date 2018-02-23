package com.example.agykoala.coursach.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.agykoala.coursach.R;
import com.example.agykoala.coursach.activitys.MainActivity;
import com.example.agykoala.coursach.dataType.Point;
import com.example.agykoala.coursach.interfaces.OnDataPass;

/**
 * A simple {@link Fragment} subclass.
 */
public class FunctionalTools extends Fragment implements View.OnClickListener {
    Button add, stop;
    EditText x, y;
    OnDataPass onDataPass;
    MainActivity ma;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onDataPass = (OnDataPass) activity;
        ma = (MainActivity) activity;
    }

    public FunctionalTools() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_functional_tools,null);
        add = (Button)v.findViewById(R.id.addButton);
        stop = (Button)v.findViewById(R.id.stopButton);
        add.setOnClickListener(this);
        stop.setOnClickListener(this);
        x = (EditText)v.findViewById(R.id.pointX);
        y = (EditText)v.findViewById(R.id.pointY);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addButton:
                if (x.getText()==null || y.getText()==null){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Incorrect input").setMessage("X or Y not enter try again")
                    .setCancelable(false).setNegativeButton("Ok, let's try",
                            (dialog, id) -> dialog.cancel());
                    AlertDialog alert = builder.create();
                    alert.show();
                }else {
                    onDataPass.onDataPass(x.getText().toString(),y.getText().toString());
                    x.setText("");
                    y.setText("");
                }
                break;
            case R.id.stopButton:
                add.setEnabled(false);
                stop.setEnabled(false);
                x.setEnabled(false);
                y.setEnabled(false);
                getActivity().findViewById(R.id.buildGraph).setEnabled(true);
                break;
        }
    }
}
