package com.vk.ecoach.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.vk.ecoach.R;
import com.vk.ecoach.model.Job;

public class HomeDriverActivity extends AppCompatActivity {

    Button btnaddjourney, btnaddemergency, btnfilljobcard;

    void initviews(){

        btnaddjourney=findViewById(R.id.buttonAddJourney);
        btnaddemergency=findViewById(R.id.buttonAddEmergency);
        btnfilljobcard=findViewById(R.id.buttonfillJobcard);

        btnaddjourney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(HomeDriverActivity.this,DriverJourneyActivity.class);
                startActivity(intent);
            }
        });

        btnaddemergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(HomeDriverActivity.this,DriverEmergencyActivity.class);
                startActivity(intent);

            }
        });

        btnfilljobcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeDriverActivity.this, JobCardActivity.class);
                startActivity(intent);
            }
        });


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,301,1,"My Journeys");
        menu.add(1,302,1,"My JobCards");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id== 101){
            Intent intent= new Intent(HomeDriverActivity.this, AllBusesActivity.class);
            startActivity(intent);
        }else if (id== 107){
            Intent intent= new Intent(HomeDriverActivity.this, WorkHomeActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_driver);
        initviews();
    }
}
