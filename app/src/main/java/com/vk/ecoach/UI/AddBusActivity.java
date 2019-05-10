package com.vk.ecoach.UI;

import android.content.ContentResolver;
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
import com.vk.ecoach.model.Bus;
import com.vk.ecoach.model.Util;

public class AddBusActivity extends AppCompatActivity {

    TextView txtHeading;

    EditText txtbusno, txtbuskm, txtbusonroute, txtbusmnfyear, txtbusengine, txtbusbattery,txtbusairfiltera,txtbusengineoila,txtbusdieselfiltera, txtbusservicedatea, txtbusserviceduea, txtbusbreakb, txtbusbearingb, txtbusgreaseb, txtbusservicedateb, txtbusservicedueb;
    Button btnAdd;
    Bus bus;

    boolean updateMode;

    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    FirebaseFirestore db;

    void initViews(){


        auth = FirebaseAuth.getInstance();
        firebaseUser=auth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        txtHeading=findViewById(R.id.textviewHeading);

        txtbusno=findViewById(R.id.editTextBusno);
        txtbuskm=findViewById(R.id.editTextBuskm);
        txtbusonroute=findViewById(R.id.editTextBusOnroute);
        txtbusmnfyear=findViewById(R.id.editTextBusMnfyear);
        txtbusengine=findViewById(R.id.editTextBusEngine);
        txtbusbattery=findViewById(R.id.editTextBusBattery);
        txtbusairfiltera=findViewById(R.id.editTextBusAirfilter);
        txtbusengineoila=findViewById(R.id.editTextBusEngineoil);
        txtbusdieselfiltera=findViewById(R.id.editTextBusDieselfilter);
        txtbusservicedatea=findViewById(R.id.editTextBusServicedatea);
        txtbusserviceduea=findViewById(R.id.editTextBusServiceduea);
        txtbusbreakb=findViewById(R.id.editTextBusBreaks);
        txtbusbearingb=findViewById(R.id.editTextBusBearing);
        txtbusgreaseb=findViewById(R.id.editTextBusGrease);
        txtbusservicedateb=findViewById(R.id.editTextBusServicedateb);
        txtbusservicedueb=findViewById(R.id.editTextBusServicedueb);



        btnAdd = findViewById(R.id.ButtonAddbus);

        bus = new Bus();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bus.busno = txtbusno.getText().toString();
                bus.km = txtbuskm.getText().toString();
                bus.mnfyear = txtbusmnfyear.getText().toString();
                bus.onroute = txtbusonroute.getText().toString();
                bus.engine = txtbusengine.getText().toString();
                bus.battery = txtbusbattery.getText().toString();
                bus.engineoil = txtbusengineoila.getText().toString();
                bus.airfilter = txtbusairfiltera.getText().toString();
                bus.dieselfilter = txtbusdieselfiltera.getText().toString();
                bus.aservicedate = txtbusservicedatea.getText().toString();
                bus.aservicedue = txtbusserviceduea.getText().toString();
                bus.breaks = txtbusbreakb.getText().toString();
                bus.bearing = txtbusbearingb.getText().toString();
                bus.grease = txtbusgreaseb.getText().toString();
                bus.bservicedate = txtbusservicedateb.getText().toString();
                bus.bservicedue = txtbusservicedueb.getText().toString();

                //addCustomerInDB();

                //add util if internet connected code here
                if (Util.isInternetConnected(AddBusActivity.this)){
                    saveCustomerInCloudDB();
                }else {

                    Toast.makeText(AddBusActivity.this, "error in set click of add bus", Toast.LENGTH_LONG).show();

                }

            }
        });

        Intent rcv = getIntent();
        updateMode = rcv.hasExtra("keyBus");

        if(updateMode){
            bus = (Bus) rcv.getSerializableExtra("keyBus");
            txtbusno.setText(bus.busno);
            txtbuskm.setText(bus.km);
            txtbusonroute.setText(bus.onroute);
            txtbusmnfyear.setText(bus.mnfyear);
            txtbusengine.setText(bus.engine);
            txtbusbattery.setText(bus.battery);
            txtbusairfiltera.setText(bus.airfilter);
            txtbusdieselfiltera.setText(bus.dieselfilter);
            txtbusengineoila.setText(bus.engineoil);
            txtbusservicedatea.setText(bus.aservicedate);
            txtbusserviceduea.setText(bus.aservicedue);
            txtbusbreakb.setText(bus.breaks);
            txtbusbearingb.setText(bus.bearing);
            txtbusgreaseb.setText(bus.grease);
            txtbusservicedateb.setText(bus.bservicedate);
            txtbusservicedueb.setText(bus.bservicedue);

            btnAdd.setText("Update Bus");
            txtHeading.setText("Update Data:");
        }
    }

    void saveCustomerInCloudDB(){

        if(updateMode){
            db.collection("Bus").document(bus.docId)
                    .set(bus)
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isComplete()){
                                Toast.makeText(AddBusActivity.this,"Updation Finished",Toast.LENGTH_LONG).show();
                                finish();
                            }else{
                                Toast.makeText(AddBusActivity.this,"Updation failed",Toast.LENGTH_LONG).show();

                            }
                        }
                    });
        }else {

            db.collection("Bus").add(bus)
                    .addOnCompleteListener(this, new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isComplete()) {
                                Toast.makeText(AddBusActivity.this, "Bus saved in db", Toast.LENGTH_LONG).show();
                                clearFields();
                            }


                        }
                    });
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus);
        initViews();


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,101,1,"All Buses");
        menu.add(1,107,1,"Home");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id== 101){
            Intent intent= new Intent(AddBusActivity.this, AllBusesActivity.class);
            startActivity(intent);
        }else if (id== 107){
            Intent intent= new Intent(AddBusActivity.this, WorkHomeActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    void clearFields() {
        txtbusno.setText("");
        txtbuskm.setText("");
        txtbusonroute.setText("");
        txtbusmnfyear.setText("");
        txtbusengine.setText("");
        txtbusbattery.setText("");
        txtbusairfiltera.setText("");
        txtbusdieselfiltera.setText("");
        txtbusengineoila.setText("");
        txtbusservicedatea.setText("");
        txtbusserviceduea.setText("");
        txtbusbreakb.setText("");
        txtbusbearingb.setText("");
        txtbusgreaseb.setText("");
        txtbusservicedateb.setText("");
        txtbusservicedueb.setText("");
    }
}

