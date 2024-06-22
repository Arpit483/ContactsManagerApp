package com.example.contactsmanagerapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class MainActivityClickHandler {

    Context context;

    public MainActivityClickHandler(Context context) {
        this.context = context;
    }

    public void onFABclicked(View view){
        Intent i=new Intent(view.getContext(),AddNewContact.class );
        context.startActivity(i);

    }

}
