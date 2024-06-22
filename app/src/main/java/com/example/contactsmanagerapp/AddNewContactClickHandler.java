package com.example.contactsmanagerapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class AddNewContactClickHandler {

    Contacts contact;
    Context context;
    ViewModal myViewModal;

    public AddNewContactClickHandler(Contacts contact, Context context , ViewModal myViewModal) {
        this.contact = contact;
        this.context = context;
        this.myViewModal=myViewModal;
    }

    public void onSubmitBtnClick (View view){

        if(contact.getName()==null || contact.getEmail()==null){
            Toast.makeText(context, "Fields Cannot Be Empty", Toast.LENGTH_LONG).show();
        }
        else {
            Intent i = new Intent(context,MainActivity.class);
//            i.putExtra("Name",contact.getName());
//            i.putExtra("Email",contact.getEmail());
            Contacts c = new Contacts(
                    contact.getName(),
                    contact.getEmail()
            );

            myViewModal.addContacts(c);


            context.startActivity(i);
        }

    }






}
