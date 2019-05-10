package com.vk.ecoach.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.vk.ecoach.R;

import java.util.ArrayList;
import java.util.List;

public class TaskListActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    FirebaseAuth auth;
    FirebaseFirestore db;
    FirebaseUser firebaseUser;

    int position;

    TextView txtbusno, txtdate, txttype;

    void initviews(){

        recyclerView=findViewById(R.id.recyclerView);

        auth = FirebaseAuth.getInstance();
        db= FirebaseFirestore.getInstance();
        firebaseUser = auth.getCurrentUser();


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(TaskListActivity.this,AddScheduledTask.class);
                startActivity(intent);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    /*

    void  fetchCustomersFromCloudDB(){

        db.collection("users").document(firebaseUser.getUid())
                .collection("customers").get()
                .addOnCompleteListener(this, new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isComplete()){

                            customers = new ArrayList<>();

                            QuerySnapshot querySnapshot = task.getResult();
                            List<DocumentSnapshot> documentSnapshots = querySnapshot.getDocuments();

                            for(DocumentSnapshot snapshot : documentSnapshots){

                                String docId = snapshot.getId();
                                Customer customer = snapshot.toObject(Customer.class);
                                customer.docId = docId;
                                customers.add(customer);


                            }

                            getSupportActionBar().setTitle("Total Customers :"+customers.size());

                            customersAdapter = new CustomersAdapter(AllCustomersActivity.this,R.layout.list_items,customers);

                            customersAdapter .setOnRecyclerItemClickListener(AllCustomersActivity.this);

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AllCustomersActivity.this);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            recyclerView.setAdapter(customersAdapter);

                        }else{
                            Toast.makeText(AllCustomersActivity.this,"Some error",Toast.LENGTH_LONG).show();
                        }

                    }
                });


    }
*/
}
