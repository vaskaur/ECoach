package com.vk.ecoach.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.vk.ecoach.R;
import com.vk.ecoach.model.Bus;

public class BusDetailsActivity extends AppCompatActivity {

    TextView textbusNo;
    TextView textKm;
    TextView textmnfyear;
    // TextView textmodel;
    TextView textonroute;
    TextView textengine;
    TextView textbattery;
    TextView textairfilter;
    TextView textdieselfilter;
    TextView textengineoil;
    TextView textaservicedate;
    TextView textaservicedue;
    TextView textbreaks;
    TextView textbearing;
    TextView textgrease;
    TextView textbservicedate;
    TextView textbservicedue;

    Bus bus;

    boolean ViewMode;



    void initviews(){


        textbusNo = findViewById(R.id.textViewbusno);
        textKm = findViewById(R.id.textViewbuskm);
        textmnfyear =findViewById(R.id.textViewbusmnfyear);
        textonroute = findViewById(R.id.textViewonroute);
        textengine = findViewById(R.id.textViewbusengine);
        textbattery =findViewById(R.id.textViewbusbattery);
        textairfilter = findViewById(R.id.textViewAirfilter);
        textdieselfilter = findViewById(R.id.textViewDieselfilter);
        textengineoil = findViewById(R.id.textViewengineoil);
        textaservicedate = findViewById(R.id.textViewservicedatea);
        textaservicedue = findViewById(R.id.textViewserviceduea);
        textbreaks = findViewById(R.id.textViewbreak);
        textbearing = findViewById(R.id.textViewbearingcheck);
        textgrease = findViewById(R.id.textViewgrease);
        textbservicedate = findViewById(R.id.textViewservicedateb);
        textbservicedue = findViewById(R.id.textViewservicedueb);

        bus= new Bus();


        Intent rcv = getIntent();
        ViewMode = rcv.hasExtra("keyBus");
        if(ViewMode){
            bus = (Bus) rcv.getSerializableExtra("keyBus");
            textbusNo.setText(bus.busno);
            textKm.setText(bus.km);
            textmnfyear.setText(bus.mnfyear);
            textonroute.setText(bus.onroute);
            textengine.setText(bus.engine);
            textbattery.setText(bus.battery);
            textairfilter.setText(bus.airfilter);
            textdieselfilter.setText(bus.dieselfilter);
            textengineoil.setText(bus.engineoil);
            textaservicedate.setText(bus.aservicedate);
            textaservicedue.setText(bus.aservicedue);
            textbreaks.setText(bus.breaks);
            textbearing.setText(bus.bearing);
            textgrease.setText(bus.grease);
            textbservicedate.setText(bus.bservicedate);
            textbservicedue.setText(bus.bservicedue);



        }


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_details);
        initviews();


    }

    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(1,106,1,"Home");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id== 106){
            Intent intent= new Intent(BusDetailsActivity.this, WorkHomeActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }




}
