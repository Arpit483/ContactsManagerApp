package com.example.contactsmanagerapp;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private final ContactDao contactDao;
    ExecutorService executor;
    Handler handler;

    public Repository(Application application) {

        ContactDataBase contactDataBase=ContactDataBase.getInstance(application);
        this.contactDao=contactDataBase.getContactDao();


        //USED FOR BACKGROUND DATABASE OPERATIONS
        executor= Executors.newSingleThreadExecutor();

        //USED FOR UPDATING UI(Use Handler of os not lib)
        handler=new Handler(Looper.getMainLooper());

       
    }

    //Methods in DAO to be called in repository
    public void addContact(Contacts contacts){


        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDao.insert(contacts);
            }
        });
    }
    public void deleteContact(Contacts contacts){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDao.delete(contacts);
            }
        });
    }
    public LiveData<List<Contacts>> getAllContacts(){
       return contactDao.getAllContacts();
    }


}
