package com.vk.ecoach.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.vk.ecoach.R;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    EditText BusNo;
    Button Submitbtn;

    FirebaseFirestore db;






    void initviews(){

        BusNo=findViewById(R.id.editTextBusNo);
        Submitbtn=findViewById(R.id.buttonsubmitNo);

        db= FirebaseFirestore.getInstance();

        Submitbtn.setOnClickListener(this);

    }
     /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        initviews();

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.service_options_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int spinnerposition = position;

                //add position 0 with --select option   make toast please select an option
                if(position==0) {

                    Toast.makeText(getBaseContext(), "Please select an option", Toast.LENGTH_LONG).show();

                    // 0- service due, 1- checkup, 2- accident

                }else if(position==1){
                 //   Intent intent= new Intent(ServiceActivity.this,busdetails)
                    Toast.makeText(getBaseContext(),"Service option selected",Toast.LENGTH_LONG).show();

                }else if(position==2){
                    //Intent intent= new Intent(ServiceActivity.this,filljobcard and show updated status)
                    //job card is comment by driver so add the name ofth edriver to the job card

                    Toast.makeText(getBaseContext(),"Check-up option selected",Toast.LENGTH_LONG).show();

                }else if(position==3){

                    //   Intent intent= new Intent(ServiceActivity.this,upload accident image with cause and comments with date)

                    Toast.makeText(getBaseContext()," Accident option selected",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(ServiceActivity.this,"Please select an option first",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
              //  Toast.makeText(this,"Please select an option first",Toast.LENGTH_LONG).show();

            }
        });


    }

    */


    @Override
    public void onClick(View v) {








/*
        if(position==0) {

            Toast.makeText(getBaseContext(), "Please select an option", Toast.LENGTH_LONG).show();

            // 0- service due, 1- checkup, 2- accident

        }else if(position==1){
            //   Intent intent= new Intent(ServiceActivity.this,busdetails)
            Toast.makeText(getBaseContext(),"Service option selected",Toast.LENGTH_LONG).show();

        }else if(position==2){
            //Intent intent= new Intent(ServiceActivity.this,filljobcard and show updated status)
            //job card is comment by driver so add the name ofth edriver to the job card

            Toast.makeText(getBaseContext(),"Check-up option selected",Toast.LENGTH_LONG).show();

        }else if(position==3){

            //   Intent intent= new Intent(ServiceActivity.this,upload accident image with cause and comments with date)

            Toast.makeText(getBaseContext()," Accident option selected",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(ServiceActivity.this,"Please select an option first",Toast.LENGTH_LONG).show();
        }
*/

    }
}