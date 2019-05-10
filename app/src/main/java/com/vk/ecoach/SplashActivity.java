package com.vk.ecoach;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vk.ecoach.UI.DriverEmergencyActivity;
import com.vk.ecoach.UI.DriverHomeActivity;
import com.vk.ecoach.UI.HomeDriverActivity;
import com.vk.ecoach.UI.JobCardActivity;
import com.vk.ecoach.UI.ScheduledTaskActivity;
import com.vk.ecoach.UI.WorkHomeActivity;

public class SplashActivity extends AppCompatActivity {



  //  FirebaseAuth auth;
  //  FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        //auth = FirebaseAuth.getInstance();
       // user= auth.getCurrentUser();

       // if(user==null) {

            handler.sendEmptyMessageDelayed(101, 3000);
        //}else{
         //   handler.sendEmptyMessageDelayed(201, 3000);
    //    }

    }



    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
           // if(msg.what == 101){
                // Intent intent = new Intent(SplashActivity.this, ListViewActivity.class);
                Intent intent = new Intent(SplashActivity.this, HomeDriverActivity.class);
                startActivity(intent);
                finish();
            //}else{
               // Intent intent = new Intent(SplashActivity.this, OtpActivity.class);
               // startActivity(intent);
                finish();
         //   }
        }
    };

}
