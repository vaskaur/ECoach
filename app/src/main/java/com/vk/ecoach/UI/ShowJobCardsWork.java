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
import com.vk.ecoach.adapter.JobCardAdapter;
import com.vk.ecoach.model.Bus;
import com.vk.ecoach.model.Job;
import com.vk.ecoach.model.Util;

import java.util.ArrayList;
import java.util.List;

public class ShowJobCardsWork extends AppCompatActivity implements OnRecyclerItemClickListener {

    RecyclerView recyclerView;

    ArrayList<Job> jobs;

    JobCardAdapter jobCardAdapter;

    FirebaseAuth auth;
    FirebaseFirestore db;
    FirebaseUser firebaseUser;

    int position;

    Job job;

    void initViews(){

        recyclerView=findViewById(R.id.RecyclerViewShowJob);

        auth = FirebaseAuth.getInstance();
        db= FirebaseFirestore.getInstance();
        firebaseUser = auth.getCurrentUser();

        if(Util.isInternetConnected(this)){

            fetchJobsFromCloudDB();

        }else{

            Toast.makeText(ShowJobCardsWork.this,"Please connect to internet and try again",Toast.LENGTH_LONG).show();
        }
    }

    void fetchJobsFromCloudDB(){


        db.collection("Jobcard").get()
                .addOnCompleteListener(this, new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isComplete()){

                            jobs = new ArrayList<>();

                            QuerySnapshot querySnapshot = task.getResult();
                            List<DocumentSnapshot> documentSnapshots = querySnapshot.getDocuments();

                            for(DocumentSnapshot snapshot : documentSnapshots){

                                String docId = snapshot.getId();
                                Job job = snapshot.toObject(Job.class);
                                job.docId = docId;
                                jobs.add(job);


                            }

                            getSupportActionBar().setTitle("Total Jobs ");

                            jobCardAdapter = new JobCardAdapter(ShowJobCardsWork.this,R.layout.show_job_card_list,jobs);

                            jobCardAdapter .setOnRecyclerItemClickListener(ShowJobCardsWork.this);

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShowJobCardsWork.this);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            recyclerView.setAdapter(jobCardAdapter);

                        }else{
                            Toast.makeText(ShowJobCardsWork.this,"Some error",Toast.LENGTH_LONG).show();
                        }

                    }
                });




    }

    void  showJobDetails(){


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(job.busnojob+" Details :\n\n\n\n");
       // builder.setTitle(job.toString());
        builder.setMessage(job.toString());
        builder.setPositiveButton("Done",null);
       // builder.setView(R.layout.dialog_view);
        AlertDialog dialog = builder.create();
        //dialog.setContentView(R.layout.dialog_view);
        // dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        // Window window= dialog.getWindow();
        //window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        dialog.show();



    }

    void showOptions(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] items = {"View"+job.busnojob,"Mark as Done" +job.busnojob, "Delete"+job.busnojob,"Cancel"};
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which){

                    case 0:
                       showJobDetails();
                        break;


                    case 1:


                        /*

                        Intent intenti = new Intent(ShowJobCardsWork.this,AddBusActivity.class);
                        intenti.putExtra("keyBus",bus);
                        startActivity(intenti);
                                                      */
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
        setContentView(R.layout.activity_show_job_cards_work);
        initViews();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,202,1,"Home");
      //  menu.add(1,202,1,"Home");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id== 202){
            Intent intent= new Intent(ShowJobCardsWork.this, WorkHomeActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(int position) {

        this.position = position;
        job=jobs.get(position);

        showOptions();

    }
}
