package com.vk.ecoach.UI;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.vk.ecoach.Listener.OnRecyclerItemClickListener;
import com.vk.ecoach.R;
import com.vk.ecoach.adapter.BusAdapter;
import com.vk.ecoach.adapter.BusDataAdapter;
import com.vk.ecoach.model.Bus;
import com.vk.ecoach.model.Util;

import java.util.ArrayList;
import java.util.List;

public class BusDataActivity extends AppCompatActivity implements OnRecyclerItemClickListener {

    RecyclerView recyclerView;

    ArrayList<Bus> busess;

    BusDataAdapter busDataAdapter;

    FirebaseAuth auth;
    FirebaseFirestore db;
    FirebaseUser firebaseUser;

    int position;
    Bus bus;

    void initViews(){

        recyclerView=findViewById(R.id.recyclerview);

        auth = FirebaseAuth.getInstance();
        db= FirebaseFirestore.getInstance();
        firebaseUser = auth.getCurrentUser();

        if(Util.isInternetConnected(this)){

            fetchBusesFromCloudDB();

        }else{

            Toast.makeText(BusDataActivity.this,"Please connect to internet and try again",Toast.LENGTH_LONG).show();
        }
    }

    void  fetchBusesFromCloudDB(){

        db.collection("Bus").get()
                .addOnCompleteListener(this, new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isComplete()){

                            busess = new ArrayList<>();

                            QuerySnapshot querySnapshot = task.getResult();
                            List<DocumentSnapshot> documentSnapshots = querySnapshot.getDocuments();

                            for(DocumentSnapshot snapshot : documentSnapshots){

                                String docId = snapshot.getId();
                                Bus bus = snapshot.toObject(Bus.class);
                                bus.docId = docId;
                                busess.add(bus);


                            }

                            getSupportActionBar().setTitle("Total Customers ");

                            busDataAdapter = new BusDataAdapter(BusDataActivity.this,R.layout.bus_data_list,busess);

                            busDataAdapter.setOnRecyclerItemClickListener(BusDataActivity.this);

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BusDataActivity.this);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            recyclerView.setAdapter(busDataAdapter);

                        }else{
                            Toast.makeText(BusDataActivity.this,"Some error",Toast.LENGTH_LONG).show();
                        }

                    }
                });


    }

    void showOptions(){
        /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] items = {"View"+bus.busno,"Update" +bus.busno, "Delete"+bus.busno,"Cancel"};
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which){

                    case 0:   */


                        Intent intent = new Intent(BusDataActivity.this,AllBusesActivity.class);
                        //intent.putExtra("keyBus",bus);
                        startActivity(intent);

                        /* break;

                }

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();  */
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_data);
        initViews();
    }

    @Override
    public void onItemClick(int position) {
        this.position = position;
        bus=busess.get(position);

        showOptions();

    }
}
