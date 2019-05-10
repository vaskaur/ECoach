package com.vk.ecoach.UI;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.vk.ecoach.model.Journey;
import com.vk.ecoach.model.Util;

import java.text.DateFormat;
import java.util.Date;

public class DriverJourneyActivity extends AppCompatActivity {

    EditText editjbusno, editjstart, editjdestination;
    TextView editjdate;
    Button submitj;

    String Date;

    Journey journey;

    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    FirebaseFirestore db;

    void initViews(){


        auth = FirebaseAuth.getInstance();
        firebaseUser=auth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        editjbusno=findViewById(R.id.editTextBusnoJ);
        editjdate=findViewById(R.id.editTextBusJDate);
        String Date= DateFormat.getDateTimeInstance().format(new Date());
        editjdate.setText(Date);
        editjstart=findViewById(R.id.editTextBusJStart);
        editjdestination=findViewById(R.id.editTextBusJDestination);
        submitj=findViewById(R.id.ButtonSubmitJob);

        journey = new Journey();


        submitj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                journey.busnoj=editjbusno.getText().toString();
                journey.datej=editjdate.getText().toString();
                journey.startj=editjstart.getText().toString();
                journey.destinationj=editjdestination.getText().toString();

                if (Util.isInternetConnected(DriverJourneyActivity.this)){
                    saveJourneyInCloudDB();
                }else {

                    Toast.makeText(DriverJourneyActivity.this, "error in set click", Toast.LENGTH_LONG).show();

                }



            }
        });




    }


    void saveJourneyInCloudDB(){

        db.collection("Jobcard").add(journey)
                .addOnCompleteListener(this, new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isComplete()) {
                            Toast.makeText(DriverJourneyActivity.this, "Journey Added", Toast.LENGTH_LONG).show();
                            clearFields();
                            Intent intent = new Intent(DriverJourneyActivity.this,DriverHomeActivity.class);
                            startActivity(intent);
                        }


                    }
                });


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_journey);
        initViews();
    }

    void clearFields() {
        editjbusno.setText("");
        editjdate.setText("");
        editjstart.setText("");
        editjdestination.setText("");

    }


}
