package com.vk.ecoach.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.vk.ecoach.R;
import com.vk.ecoach.model.Driver;
import com.vk.ecoach.model.Worker;
import com.vk.ecoach.model.admin;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextEmail, editTextPassword;
    Button buttonLogin;
    RadioGroup loginradio;
    RadioButton adminbtn, worksmanagerbtn, driverbtn;

    Worker worker;
    Driver driver;
    admin Admin;

    FirebaseAuth auth;

    ProgressDialog progressDialog;

    void initViews() {

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        loginradio = findViewById(R.id.radioGroup);
        adminbtn = findViewById(R.id.radioButtonAdmin);
        worksmanagerbtn = findViewById(R.id.radioButtonWorkshop);
        driverbtn = findViewById(R.id.radioButtonDriver);

        buttonLogin.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");
        progressDialog.setCancelable(false);


        auth = FirebaseAuth.getInstance();


        loginradio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selctedradiobtnid = loginradio.getCheckedRadioButtonId();

            //  if (selctedradiobtnid == 0) {
                if(selctedradiobtnid==R.id.radioButtonAdmin) {
                    Admin = new admin();
                    auth = FirebaseAuth.getInstance();

                    //   } else if (selctedradiobtnid == 1) {
                }  else if(selctedradiobtnid==R.id.radioButtonWorkshop){
                    worker = new Worker();
                    auth = FirebaseAuth.getInstance();

               // } else if (selctedradiobtnid == 2) {
                }  else if(selctedradiobtnid==R.id.radioButtonDriver){
                    driver = new Driver();
                    auth = FirebaseAuth.getInstance();

                } else {
                       Toast.makeText(LoginActivity.this,"Please select type ",Toast.LENGTH_LONG).show();
                }
            }

        });


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    @Override
    public void onClick(View v) {
        int selctedradiobtnid = loginradio.getCheckedRadioButtonId();
       if(selctedradiobtnid==R.id.radioButtonAdmin) {
           //  int id = v.getId();
           //  if(id==R.id.radioButtonAdmin){
           Admin.email = editTextEmail.getText().toString();
           Admin.password = editTextPassword.getText().toString();

           progressDialog.show();

           auth.signInWithEmailAndPassword(Admin.email, Admin.password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if (task.isComplete()) {
                       Intent intent = new Intent(LoginActivity.this, AdminHomeActivity.class);
                       startActivity(intent);
                       finish();
                   }
               }
           });

           //}else if(id==R.id.radioButtonWorkshop){
       }else if(selctedradiobtnid==R.id.radioButtonWorkshop){

              worker.email = editTextEmail.getText().toString();
              worker.password = editTextPassword.getText().toString();

              progressDialog.show();

              auth.signInWithEmailAndPassword(worker.email, worker.password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      if (task.isComplete()) {
                          Intent intent = new Intent(LoginActivity.this, WorkHomeActivity.class);
                          startActivity(intent);
                          finish();
                      }
                  }
              });

       // }else if(id==R.id.radioButtonDriver){
       }else if(selctedradiobtnid==R.id.radioButtonDriver){

              driver.email = editTextEmail.getText().toString();
              driver.password = editTextPassword.getText().toString();

              progressDialog.show();

              auth.signInWithEmailAndPassword(driver.email, driver.password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      if (task.isComplete()) {
                          Intent intent = new Intent(LoginActivity.this, Driver.class);
                          startActivity(intent);
                          finish();
                      }
                  }
              });

        }else{
              Toast.makeText(LoginActivity.this,"some error",Toast.LENGTH_LONG).show();
          }


    }



}
