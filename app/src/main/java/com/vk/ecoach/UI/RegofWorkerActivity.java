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
import com.vk.ecoach.R;
import com.vk.ecoach.model.Util;
import com.vk.ecoach.model.Worker;

public class RegofWorkerActivity extends AppCompatActivity  implements View.OnClickListener {

    EditText eTxtName, eTxtEmail, eTxtPassword;
    Button btnregister;
    TextView textlogin;

    Worker worker;

    FirebaseAuth auth;
    FirebaseFirestore db;
    FirebaseUser firebaseUser;

    FirebaseInstanceId firebaseInstanceId;
    //FirebaseMessaging firebaseMessaging;

    ProgressDialog progressDialog;

    void initViews(){
        eTxtName=findViewById(R.id.eName);
        eTxtEmail=findViewById(R.id.eEmail);
        eTxtPassword=findViewById(R.id.ePassword);
        textlogin= findViewById(R.id.textLogin);
        btnregister=findViewById(R.id.buttonR);

        btnregister.setOnClickListener(this);
        textlogin.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");
        progressDialog.setCancelable(false);

        worker= new Worker();

        auth=FirebaseAuth.getInstance();
        db= FirebaseFirestore.getInstance();
        firebaseInstanceId=FirebaseInstanceId.getInstance();
       // firebaseMessaging=FirebaseMessaging.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regof_worker);
        initViews();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.buttonR) {

            worker.name= eTxtName.getText().toString();
            worker.email = eTxtEmail.getText().toString();
            worker.password = eTxtPassword.getText().toString();

            if (Util.isInternetConnected(this)) {

                registerUser();
            }else{

                Toast.makeText(this,"please connect to internet",Toast.LENGTH_LONG).show();
            }
        } else {

            Intent intent = new Intent(RegofWorkerActivity.this, LoginActivity.class);
            startActivity(intent);
        }

    }

//    void subscribeForCloudMessaging(){
//        firebaseMessaging.subscribeToTopic("events").addOnCompleteListener(this, new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if(task.isSuccessful()){
//                    getToken();
//                }
//            }
//        });
//    }

    void getToken(){
        firebaseInstanceId.getInstanceId().addOnCompleteListener(this, new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if(task.isComplete()){
                   // worker.Token=task.getResult().getToken();
                    saveUserInCloudDB();
                }
            }
        });

    }

    void registerUser(){
        progressDialog.show();

        auth.createUserWithEmailAndPassword(worker.email,worker.password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isComplete()) {
//                    Toast.makeText(RegisterationActivity.this, userr.Name + "Registered Sucessfully", Toast.LENGTH_LONG).show();
//                    progressDialog.dismiss();
//
//                    Intent intent = new Intent(RegisterationActivity.this, HomeActivity.class);
//                    startActivity(intent);
//                    finish();

                    //saveUserInCloudDB();

                  //  subscribeForCloudMessaging();

                }


            }
        });
    }

    void saveUserInCloudDB(){

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
        db.collection("users").document(firebaseUser.getUid()).set(worker).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(RegofWorkerActivity.this, worker.name + "Registered Sucessfully", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();

                Intent intent = new Intent(RegofWorkerActivity.this, WorkHomeActivity.class);
                startActivity(intent);
                finish();
            }

        });

    }


}
