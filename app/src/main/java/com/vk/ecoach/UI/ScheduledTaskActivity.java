package com.vk.ecoach.UI;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
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
import com.vk.ecoach.adapter.ScheduleAdapter;
import com.vk.ecoach.model.Bus;
import com.vk.ecoach.model.Schedule;
import com.vk.ecoach.model.Util;

import java.util.ArrayList;
import java.util.List;

public class ScheduledTaskActivity extends AppCompatActivity implements OnRecyclerItemClickListener {

    RecyclerView recyclerView;

    ArrayList<Schedule> schedules;

    ScheduleAdapter scheduleAdapter;

    FirebaseAuth auth;
    FirebaseFirestore db;
    FirebaseUser firebaseUser;

    int position;

    TextView txtbusno, txtdate, txttype;

    Schedule schedule;

    void initviews(){
        recyclerView=findViewById(R.id.recyclerviewtaskschedule);

        auth = FirebaseAuth.getInstance();
        db= FirebaseFirestore.getInstance();
        firebaseUser = auth.getCurrentUser();

        if(Util.isInternetConnected(this)){

          //  fetchBusesFromCloudDB();

        }else{

            Toast.makeText(ScheduledTaskActivity.this,"Please connect to internet and try again",Toast.LENGTH_LONG).show();
        }



    }

    void  fetchBusesFromCloudDB(){

        db.collection("ScheduledTasks").get()
                .addOnCompleteListener(this, new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isComplete()){

                            schedules = new ArrayList<>();

                            QuerySnapshot querySnapshot = task.getResult();
                            List<DocumentSnapshot> documentSnapshots = querySnapshot.getDocuments();

                            for(DocumentSnapshot snapshot : documentSnapshots){

                                String docId = snapshot.getId();
                                Schedule schedule = snapshot.toObject(Schedule.class);
                                schedule.docId = docId;
                                schedules.add(schedule);


                            }

                            getSupportActionBar().setTitle("Total Schedules ");

                            scheduleAdapter = new ScheduleAdapter(ScheduledTaskActivity.this,R.layout.list_schedule,schedules);

                            scheduleAdapter.setOnRecyclerItemClickListener(ScheduledTaskActivity.this);

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ScheduledTaskActivity.this);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            recyclerView.setAdapter(scheduleAdapter);

                        }else{
                            Toast.makeText(ScheduledTaskActivity.this,"Some error",Toast.LENGTH_LONG).show();
                        }

                    }
                });


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduled_task);
        initviews();
    }

    @Override
    public void onItemClick(int position) {

        //incomplete write code to display a dialog box to cancel the task or reschedule or mark as done

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,103,1,"Add Schedule");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id== 103){
            Intent intent= new Intent(ScheduledTaskActivity.this, AddScheduledTask.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
