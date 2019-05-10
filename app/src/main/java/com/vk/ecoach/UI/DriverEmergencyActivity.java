package com.vk.ecoach.UI;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.vk.ecoach.R;
import com.vk.ecoach.model.ELocation;

import java.util.List;

public class DriverEmergencyActivity extends AppCompatActivity implements LocationListener, AdapterView.OnItemSelectedListener {


    Spinner DESpinner;

    Button btnEmergency;
    TextView textViewLocation;

    LocationManager locationManager;
    ProgressDialog progressDialog;

    ELocation eLocation;

    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    FirebaseFirestore db;


    void initViews(){

        auth = FirebaseAuth.getInstance();
        firebaseUser=auth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

       DESpinner=findViewById(R.id.spinneremergencyD);

     //   Spinner DESpinner = (Spinner) findViewById(R.id.spinneremergencyD);
        DESpinner.setOnItemSelectedListener(this);
// Create an ArrayAdapter using the string array and a default spinner layout

     //   String[] myItems= getResources().getStringArray(R.array.Emergency_options_array);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Emergency_options_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       // adapter.add("~~Select an issue~~");
// Apply the adapter to the spinner
        DESpinner.setAdapter(adapter);




        textViewLocation=findViewById(R.id.textViewLocation);
        
        btnEmergency=findViewById(R.id.buttonemergency);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        locationManager=(LocationManager) getSystemService(LOCATION_SERVICE);
        btnEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(DriverEmergencyActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(DriverEmergencyActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(DriverEmergencyActivity.this,"Please Grant Permissions in Settings and Try Again",Toast.LENGTH_LONG).show();
                }else {
                    progressDialog.show();
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1 * 60, 10, DriverEmergencyActivity.this);
                   // btnEmergency.setText("Send Location");
                }
            }
        });


        //DESpinner.setOnItemSelectedListener(this);


    }

    public void onLocationChanged(Location location) {



        // Geo-Coding -> Getting co-ordinates form CTT/GPS
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        //txtLocation.setText("Location: "+latitude+" : "+longitude);

        // If we wish to fetch location only once
        locationManager.removeUpdates(this);

        try {

            Geocoder geocoder = new Geocoder(this);
            List<Address> addresses = geocoder.getFromLocation(latitude,longitude,1);

            StringBuffer buffer = new StringBuffer();

            if(addresses!=null && addresses.size()>0) {
                Address address = addresses.get(0);



                for(int i=0;i<=address.getMaxAddressLineIndex();i++){
                    buffer.append(address.getAddressLine(i)+"\n");


                }

            }

            textViewLocation.setText("Location: "+latitude+" : "+longitude+"\nAddress:"+buffer.toString());





            Intent intent = new Intent(DriverEmergencyActivity.this, MapsActivity.class);
            intent.putExtra("latitude",latitude);
            intent.putExtra("longitude",longitude);
            intent.putExtra("address",buffer.toString());
            Toast.makeText(DriverEmergencyActivity.this, "Location fetched Successfully", Toast.LENGTH_LONG).show();
            startActivity(intent);











            //    SmsManager smsManager = SmsManager.getDefault();

            //String phone = "+91 79738 17360";
            // String message = "vasman. This is my location"+latitude+":"+longitude+"address"+buffer.toString();
            //  smsManager.sendTextMessage(phone,null,message,null,null);



        }catch (Exception e){
            e.printStackTrace();
        }

        progressDialog.dismiss();

    }

   void saveLocInCloudDb(){
        db.collection("Location").add(eLocation)
                .addOnCompleteListener(this, new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isComplete()) {
                            Toast.makeText(DriverEmergencyActivity.this, "Location", Toast.LENGTH_LONG).show();
                          //  clearFields();
                        }


                    }
                });
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_emergency);
        initViews();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
