package com.vk.ecoach.UI;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.vk.ecoach.R;
import com.vk.ecoach.model.Job;
import com.vk.ecoach.model.Util;

import java.text.DateFormat;
import java.util.Date;

public class JobCardActivity extends AppCompatActivity {

    EditText editjobbusno, editjobcomments;
    TextView editjobbusdate;
    Button submitjob;

    String Date;

    Job job;

    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    FirebaseFirestore db;

    void initViews(){


        auth = FirebaseAuth.getInstance();
        firebaseUser=auth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

      editjobbusno=findViewById(R.id.editTextBusnoJob);
      editjobbusdate = (TextView) findViewById(R.id.editTextBusJobDate);
      String Date= DateFormat.getDateTimeInstance().format(new Date());
      editjobbusdate.setText(Date);

      editjobcomments=findViewById(R.id.editTextBusJobComments);

      submitjob=findViewById(R.id.ButtonSubmitJob);

      job= new Job();


      submitjob.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              job.busnojob = editjobbusno.getText().toString();
              job.date=editjobbusdate.getText().toString();
              job.comments = editjobcomments.getText().toString();

              if (Util.isInternetConnected(JobCardActivity.this)){
                  saveCardInCloudDB();
              }else {

                  Toast.makeText(JobCardActivity.this, "error in set click", Toast.LENGTH_LONG).show();

              }


          }
      });



    }

    void saveCardInCloudDB(){

        db.collection("Jobcard").add(job)
                .addOnCompleteListener(this, new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isComplete()) {
                            Toast.makeText(JobCardActivity.this, "Job card filled", Toast.LENGTH_LONG).show();
                            clearFields();
                            Intent intent = new Intent(JobCardActivity.this,DriverHomeActivity.class);
                            startActivity(intent);
                        }


                    }
                });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_card);
        initViews();
    }



    void clearFields() {
        editjobbusno.setText("");
        editjobbusno.setText("");
        editjobcomments.setText("");

    }
}
