package com.example.contactsmanagerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactsmanagerapp.databinding.ContactListItemBinding;

import java.util.ArrayList;

public class MyAdpater extends RecyclerView.Adapter<MyAdpater.ContactViewHolder> {

    private ArrayList<Contacts> contacts;

    public MyAdpater(ArrayList<Contacts> contacts) {
        this.contacts = contacts;
    }


    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Creating new View Holder for items in Recycler View

        ContactListItemBinding contactListItemBinding =
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.contact_list_item,
                        parent,
                        false
                );

        return new ContactViewHolder(contactListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {

        //called when needed to update the items view
        Contacts currentContact=contacts.get(position);
        holder.contactListItemBinding.setContact(currentContact);

    }

    @Override
    public int getItemCount() {
        if(contacts !=null){
            return contacts.size();
        }else{
            return 0;
        }
    }


    public void setContacts(ArrayList<Contacts> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();


    }

    class ContactViewHolder extends RecyclerView.ViewHolder{
        private ContactListItemBinding contactListItemBinding;

        public ContactViewHolder(@NonNull ContactListItemBinding contactListItemBinding) {
            super(contactListItemBinding.getRoot());
            this.contactListItemBinding = contactListItemBinding;
        }
    }

}
