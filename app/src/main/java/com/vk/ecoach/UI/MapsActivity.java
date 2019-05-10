package com.vk.ecoach.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;
import com.vk.ecoach.R;
import com.vk.ecoach.model.ELocation;
import com.vk.ecoach.model.Util;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    public FloatingActionButton btnfab;
    double lat;
    double lng;


   ELocation eLocation;

   FirebaseAuth auth;
   FirebaseUser firebaseUser;
   FirebaseFirestore db;
   FirebaseInstanceId firebaseInstanceId;

    ProgressDialog progressDialog;


    void initViews(){

        auth = FirebaseAuth.getInstance();
        firebaseUser=auth.getCurrentUser();
        db = FirebaseFirestore.getInstance();


        btnfab = (FloatingActionButton) findViewById(R.id.fab);

        Intent rcv = getIntent();
        lat = rcv.getDoubleExtra("latitude", 30.9024825);
        lng = rcv.getDoubleExtra("longitude", 75.8201934);
        final String text = rcv.getStringExtra("address");

        //  eLocation.address=text;

        LatLng ldh = new LatLng(lat, lng);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        eLocation = new ELocation();
        firebaseInstanceId = FirebaseInstanceId.getInstance();




        // eLocation= new ELocation();

        btnfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            eLocation.latitude=lat;
            eLocation.longitude=lng;
            eLocation.address=text;
           // eLocation.issue=


               //   eLocation.address=



                if (Util.isInternetConnected(MapsActivity.this)){
                   saveLocInCloudDb();
                }else {

                    Toast.makeText(MapsActivity.this, "error in set click of add bus", Toast.LENGTH_LONG).show();

                }


             //   saveLocInCloudDb();


              //  Toast.makeText(MapsActivity.this, "Location Saved Successfully", Toast.LENGTH_LONG).show();
            }

        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        initViews();



    }


   void saveLocInCloudDb(){
        db.collection("Location").add(eLocation)
                .addOnCompleteListener(this, new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isComplete()) {
                            Toast.makeText(MapsActivity.this, "Location", Toast.LENGTH_LONG).show();
                            //  clearFields();
                        }


                    }
                });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        /*
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

              */

        Intent rcv = getIntent();
        lat = rcv.getDoubleExtra("latitude", 30.9024825);
        lng = rcv.getDoubleExtra("longitude", 75.8201934);
        String text = rcv.getStringExtra("address");

      //  eLocation.address=text;

        LatLng ldh = new LatLng(lat, lng);

        mMap.addMarker(new MarkerOptions().position(ldh).title(text).snippet("YOUR LOCATION"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ldh, 12));


        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        mMap.setTrafficEnabled(true);

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                return false;
            }
        });

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {


            }
        });




        /*

        To get location

 if (mGoogleApiClient == null) {
    mGoogleApiClient = new GoogleApiClient.Builder(this)
        .addConnectionCallbacks(this)
        .addOnConnectionFailedListener(this)
        .addApi(LocationServices.API)
        .build();
}
if (mGoogleApiClient != null) {
    mGoogleApiClient.connect();
}
After connection

public class MainActivity extends ActionBarActivity implements
        ConnectionCallbacks, OnConnectionFailedListener {
    ...
    @Override
    public void onConnected(Bundle connectionHint) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
            mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
        }
    }
}

         */


    }
}
