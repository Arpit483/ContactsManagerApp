package com.example.contactsmanagerapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModal extends AndroidViewModel {

    //If you need to use context inside your ViewModal
    //you should use AndroidViewModal(AVM)
    //because it contains the application context
    private Repository myRepository;


    public ViewModal(@NonNull Application application)
    {
        super(application);
        this.myRepository = new Repository(application);
    }

    //Live Data
    private LiveData<List<Contacts>> allContacts;

    public LiveData<List<Contacts>> getGetAllContacts(){
        allContacts = myRepository.getAllContacts();
        return allContacts;
    }
    public void addContacts(Contacts contacts){
        myRepository.addContact(contacts);
    }
    public void deleteContacts(Contacts contacts){
        myRepository.deleteContact(contacts);
    }


}
