package com.example.restaurante;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class Loading {
    Activity activity;
    AlertDialog alertDialog;

    public Loading(Activity main){

        this.activity=main;

    }

    public void startLoading(){
        AlertDialog.Builder builder= new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.fragment_custom_dialog,null));
        builder.setCancelable(false);
        alertDialog=builder.create();
        alertDialog.show();

    }

    public void dismissDialog(){
        alertDialog.dismiss();
    }
}
