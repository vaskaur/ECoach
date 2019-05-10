package com.vk.ecoach.UI;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.vk.ecoach.R;
import com.vk.ecoach.model.Driver;
import com.vk.ecoach.model.Util;
import com.vk.ecoach.model.Worker;

public class RegofDriverActivity extends AppCompatActivity implements View.OnClickListener {

    EditText dTxtName, dTxtEmail, dTxtPassword;
    Button btnregister;
    TextView textlogin;

    Driver driver;

    FirebaseAuth auth;
    FirebaseFirestore db;
    FirebaseUser firebaseUser;

    FirebaseInstanceId firebaseInstanceId;
    FirebaseMessaging firebaseMessaging;

    ProgressDialog progressDialog;

    void initViews() {
        dTxtName = findViewById(R.id.dName);
        dTxtEmail = findViewById(R.id.dEmail);
        dTxtPassword = findViewById(R.id.dPassword);
        textlogin = findViewById(R.id.textLogind);
        btnregister = findViewById(R.id.buttonRD);

        btnregister.setOnClickListener(this);
        textlogin.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");
        progressDialog.setCancelable(false);

        driver = new Driver();

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        firebaseInstanceId = FirebaseInstanceId.getInstance();
        firebaseMessaging=FirebaseMessaging.getInstance();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regof_driver);
        initViews();
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.buttonR) {

            driver.name = dTxtName.getText().toString();
            driver.email = dTxtEmail.getText().toString();
            driver.password = dTxtPassword.getText().toString();

            if (Util.isInternetConnected(this)) {

                registerUser();
            } else {

                Toast.makeText(this, "please connect to internet", Toast.LENGTH_LONG).show();
            }
        } else {

            Intent intent = new Intent(RegofDriverActivity.this, LoginActivity.class);
            startActivity(intent);
        }

    }

    void subscribeForCloudMessaging() {
        firebaseMessaging.subscribeToTopic("events").addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    getToken();
                }
            }
        });
    }

    void getToken() {
        firebaseInstanceId.getInstanceId().addOnCompleteListener(this, new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (task.isComplete()) {
                    driver.Token = task.getResult().getToken();
                    saveUserInCloudDB();
                }
            }
        });

    }

    void registerUser() {
        progressDialog.show();

        auth.createUserWithEmailAndPassword(driver.email, driver.password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isComplete()) {
//                    Toast.makeText(RegisterationActivity.this, userr.Name + "Registered Sucessfully", Toast.LENGTH_LONG).show();
//                    progressDialog.dismiss();
//
//                    Intent intent = new Intent(RegisterationActivity.this, HomeActivity.class);
//                    startActivity(intent);
//                    finish();

                    //saveUserInCloudDB();

                    subscribeForCloudMessaging();

                }


            }
        });
    }

    void saveUserInCloudDB() {

      /*  db.collection("users").add(userr)
        .addOnCompleteListener(this, new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {

                if(task.isComplete()) {
                    Toast.makeText(RegisterationActivity.this, userr.Name + "Registered Sucessfully", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();

                    Intent intent = new Intent(RegisterationActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });  */

        firebaseUser = auth.getCurrentUser();
        db.collection("drivers").document(firebaseUser.getUid()).set(driver).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(RegofDriverActivity.this, driver.name + "Registered Sucessfully", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();

                Intent intent = new Intent(RegofDriverActivity.this, HomeDriverActivity.class);
                startActivity(intent);
                finish();
            }

        });

    }
}
