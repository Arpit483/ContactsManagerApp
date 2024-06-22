package com.example.contactsmanagerapp;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactsmanagerapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Data Source
    private ContactDataBase contactDataBase;
    private ArrayList<Contacts> contactArrayList=new ArrayList<>();

    //Adapter
    private MyAdpater myAdpater;

    //Binding
    private ActivityMainBinding mainBinding;
    private MainActivityClickHandler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mainBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
        );

        handler = new MainActivityClickHandler(this);
        mainBinding.setClickHandler(handler);


        //Recycler View
        RecyclerView recyclerView=mainBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        //Adapter View



        //Database
        contactDataBase=ContactDataBase.getInstance(this);

        //View Model
       ViewModal viewModal= new ViewModelProvider(this).get(ViewModal.class);

        //Inserting New Conatct
//        Contacts c1=new Contacts("Jack","djqw");
//        viewModal.addContacts(c1);
        //Loading data from Room Datbase
        viewModal.getGetAllContacts().observe(this,
                new Observer<List<Contacts>>() {
                    @Override
                    public void onChanged(List<Contacts> contacts) {

                        contactArrayList.clear();


                        for(Contacts c:contacts){
                            Log.v("Tagy",c.getName());
                            contactArrayList.add(c);

                        }

                        myAdpater.notifyDataSetChanged();

                    }
                });
        myAdpater =new MyAdpater(contactArrayList);



        recyclerView.setAdapter(myAdpater);



        //Swipe to Delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                Contacts c= contactArrayList.get(viewHolder.getAdapterPosition());
                viewModal.deleteContacts(c);

            }
        }).attachToRecyclerView(recyclerView);
        








    }
}