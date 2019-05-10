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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.vk.ecoach.R;
import com.vk.ecoach.model.Driver;
import com.vk.ecoach.model.Util;

public class AddDrivers extends AppCompatActivity {

 EditText editTextdname, editTextdphone, editTextdemail, editTextdpassword, editTextdjoiningdate;
 Button addDriver;

    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    FirebaseFirestore db;

    Driver driver;

 void initviews(){

     auth = FirebaseAuth.getInstance();
     firebaseUser=auth.getCurrentUser();
     db = FirebaseFirestore.getInstance();

     editTextdname=findViewById(R.id.editTextDName);
     editTextdphone=findViewById(R.id.editTextDphno);
     editTextdemail=findViewById(R.id.editTextDEmail);
     editTextdpassword=findViewById(R.id.editTextDpassword);
     editTextdjoiningdate=findViewById(R.id.editTextDjdate);

     addDriver=findViewById(R.id.ButtonAddDriver);

     driver=new Driver();

     addDriver.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             driver.name=editTextdname.getText().toString();
             driver.email=editTextdemail.getText().toString();
             driver.phone=editTextdphone.getText().toString();
             driver.password=editTextdpassword.getText().toString();
             driver.joining=editTextdjoiningdate.getText().toString();

             if (Util.isInternetConnected(AddDrivers.this)){
                 saveDriversInCloudDB();
             }else {

                 Toast.makeText(AddDrivers.this, "error in set click of add bus", Toast.LENGTH_LONG).show();

             }

         }
     });


 }

 void saveDriversInCloudDB(){

     db.collection("Drivers").add(driver)
             .addOnCompleteListener(this, new OnCompleteListener<DocumentReference>() {
                 @Override
                 public void onComplete(@NonNull Task<DocumentReference> task) {
                     if (task.isComplete()) {
                         Toast.makeText(AddDrivers.this, "Bus saved in db", Toast.LENGTH_LONG).show();
                       //  clearFields();
                     }


                 }
             });


 }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drivers);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,103,1,"All Buses");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id== 103){
           // Intent intent= new Intent(AddDrivers.this, .class);
            //startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
