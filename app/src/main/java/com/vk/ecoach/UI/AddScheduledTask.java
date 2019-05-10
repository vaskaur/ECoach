package com.vk.ecoach.UI;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.vk.ecoach.R;
import com.vk.ecoach.model.Schedule;
import com.vk.ecoach.model.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddScheduledTask extends AppCompatActivity {

    EditText editTextBusNo, editTextType, editTextDate , editTextTime;
    Button btnSchedule;

    DatePickerDialog.OnDateSetListener mDateSetListener;
    TimePickerDialog.OnTimeSetListener mTimeListener;

    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    FirebaseFirestore db;

    Schedule schedule;

    private static final String TAG = "AddScheduledTask";

    void initviews(){

        auth = FirebaseAuth.getInstance();
        firebaseUser=auth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        editTextBusNo=findViewById(R.id.editTextBusno);
        editTextType=findViewById(R.id.editTextType);
        editTextDate=findViewById(R.id.editTextDate);
        editTextTime=findViewById(R.id.editTextTime);
        btnSchedule=findViewById(R.id.buttonSchedule);




        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(AddScheduledTask.this,mDateSetListener, year,month,day);
                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                editTextDate.setText(date);


            }
        };

        editTextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int hourofDay = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog= new TimePickerDialog(AddScheduledTask.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = hourOfDay+":"+minute;
                        editTextTime.setText(time);
                    }
                },hourofDay,minute,android.text.format.DateFormat.is24HourFormat(AddScheduledTask.this));
                //timePickerDialog.getWindow();
                timePickerDialog.show();
            }
        });

          /*
        mTimeListener= new TimePickerDialog.OnTimeSetListener(){

            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {

                String time = hourOfDay+":"+minutes;
                editTextTime.setText(time);

            }
        };
         */




        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                schedule.busnum=editTextBusNo.getText().toString();
                schedule.type=editTextType.getText().toString();
                schedule.date=editTextDate.getText().toString();

                if (Util.isInternetConnected(AddScheduledTask.this)){
                    savetaskinclouddb();
                }else {

                    Toast.makeText(AddScheduledTask.this, "error in set click of add customer", Toast.LENGTH_LONG).show();

                }



            }
        });




    }

    void savetaskinclouddb(){

        db.collection("scheduledTasks").add(schedule)
                .addOnCompleteListener(this, new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isComplete()) {
                            Toast.makeText(AddScheduledTask.this, "Task scheduled", Toast.LENGTH_LONG).show();
                            //clearFields();
                        }


                    }
                });

    }

/*
    public static void showNotification(Context context, Class<?> cls, String title, String content)
    {
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent notificationIntent = new Intent(context, cls);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(cls);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(D,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Notification notification = builder.setContentTitle(title)
                .setContentText(content).setAutoCancel(true)
                .setSound(alarmSound).setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentIntent(pendingIntent).build();

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(DAILY_REMINDER_REQUEST_CODE, notification);
    }

    public void onReceive(Context context, Intent intent) {
        //Trigger the notification
        NotificationScheduler.showNotification(context, MainActivity.class,
                "You have 5 unwatched videos", "Watch them now?");
    }

*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_scheduled_task);
        initviews();
    }

    /*  final Calendar myCalendar = Calendar.getInstance();

    EditText edittext = (EditText) findViewById(R.id.scheduleDate);
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };


    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editTextDate.setText(sdf.format(myCalendar.getTime()));
    }


    public void scheduledate(View view) {
        new DatePickerDialog(AddScheduledTask.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    public void btnschedule(View view) {
       // change intent here
        Intent intent = new Intent(getApplicationContext(),AddScheduledTask.class);
        startActivity(intent);
    } */
}