package com.vk.ecoach.UI;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Scroller;
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
import com.vk.ecoach.model.Bus;
import com.vk.ecoach.model.Util;

import java.util.ArrayList;
import java.util.List;

public class AllBusesActivity extends AppCompatActivity implements OnRecyclerItemClickListener {

    RecyclerView recyclerView;



    ArrayList<Bus> buses;

    BusAdapter busAdapter;

    FirebaseAuth auth;
    FirebaseFirestore db;
    FirebaseUser firebaseUser;

    int position;
    Bus bus;

    void initViews(){

        recyclerView=findViewById(R.id.recyclerView);

        auth = FirebaseAuth.getInstance();
        db= FirebaseFirestore.getInstance();
        firebaseUser = auth.getCurrentUser();

        if(Util.isInternetConnected(this)){

            fetchBusesFromCloudDB();

        }else{

            Toast.makeText(AllBusesActivity.this,"Please connect to internet and try again",Toast.LENGTH_LONG).show();
        }
    }

    void  fetchBusesFromCloudDB(){

        db.collection("Bus").get()
                .addOnCompleteListener(this, new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isComplete()){

                            buses = new ArrayList<>();

                            QuerySnapshot querySnapshot = task.getResult();
                            List<DocumentSnapshot> documentSnapshots = querySnapshot.getDocuments();

                            for(DocumentSnapshot snapshot : documentSnapshots){

                                String docId = snapshot.getId();
                                Bus bus = snapshot.toObject(Bus.class);
                                bus.docId = docId;
                                buses.add(bus);


                            }

                            getSupportActionBar().setTitle("Total Customers ");

                            busAdapter = new BusAdapter(AllBusesActivity.this,R.layout.list_items,buses);

                            busAdapter .setOnRecyclerItemClickListener(AllBusesActivity.this);

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AllBusesActivity.this);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            recyclerView.setAdapter(busAdapter);

                        }else{
                            Toast.makeText(AllBusesActivity.this,"Some error",Toast.LENGTH_LONG).show();
                        }

                    }
                });


    }

    void  showCustomerDetails(){

       /*
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(bus.busno+"Details :");
        builder.setTitle(bus.toString());
        builder.setPositiveButton("Done",null);
       // builder.setView(R.layout.dialog_view);
        AlertDialog dialog = builder.create();
        //dialog.setContentView(R.layout.dialog_view);
        // dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        // Window window= dialog.getWindow();
        // window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);


        dialog.show();   */



    }

    void showOptions(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] items = {"View"+bus.busno,"Update" +bus.busno, "Delete"+bus.busno,"Cancel"};
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which){

                    case 0:
                       /* showCustomerDetails();
                        break; */
                        Intent intent = new Intent(AllBusesActivity.this,BusDetailsActivity.class);
                        intent.putExtra("keyBus",bus);
                        startActivity(intent);

                        break;


                    case 1:

                        Intent intenti = new Intent(AllBusesActivity.this,AddBusActivity.class);
                        intenti.putExtra("keyBus",bus);
                        startActivity(intenti);

                        break;

                }

                }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_buses);
        initViews();
    }

    @Override
    public void onItemClick(int position) {
        this.position = position;
        bus=buses.get(position);

        showOptions();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,102,1,"Add Buses");
        menu.add(1,106,1,"Home");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id== 102){
            Intent intent= new Intent(AllBusesActivity.this, AddBusActivity.class);
            startActivity(intent);
        }else if (id== 106){
            Intent intent= new Intent(AllBusesActivity.this, WorkHomeActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
