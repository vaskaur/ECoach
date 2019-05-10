package com.vk.ecoach.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.Toast;

import com.vk.ecoach.R;
import com.vk.ecoach.storage.ImageUploadActivity;

public class WorkHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    GridLayout gridmain;
    CardView cardService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //gridLayout=(GridLayout) findViewById(R.id.);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        gridmain = findViewById(R.id.gridmain);



        setSingleEvent(gridmain);
        /*int screenWidth = getScreenWidth();
        int cellWidth = screenWidth / 4;
        int margin = cellWidth / 8;
        GridLayout.LayoutParams p;
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            p = (GridLayout.LayoutParams) gridLayout.getChildAt(i).getLayoutParams();
            p.width = cellWidth;
            p.setMargins(margin, margin, margin, margin);

        }*/
    }




    private void setSingleEvent(GridLayout gridLayout){

        for(int i =0;i<gridLayout.getChildCount();i++) {
            CardView cardView = (CardView)gridLayout.getChildAt(i);
            final int selectedcard=i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedcard == 0) {
                        Toast.makeText(WorkHomeActivity.this, "service selected", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(WorkHomeActivity.this, AllBusesActivity.class);
                        startActivity(intent);
                    } else if (selectedcard == 1) {
                        Toast.makeText(WorkHomeActivity.this, "schedule selected", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(WorkHomeActivity.this, ScheduledTaskActivity.class);
                        startActivity(intent);
                    } else if (selectedcard == 2) {
                        Toast.makeText(WorkHomeActivity.this, "Emergency selected", Toast.LENGTH_LONG).show();
                        // Intent intent=new Intent(WorkHomeActivity.this,.class);
                        //startActivity(intent);
                    } else if (selectedcard == 3) {
                        Toast.makeText(WorkHomeActivity.this, "job-card selected", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(WorkHomeActivity.this,ShowJobCardsWork.class);
                        startActivity(intent);
                    }
                }
            });
        }


    }

      /*  private int getScreenWidth(){
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            return  size.x;
        } */




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.work_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        //add logout here
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.accident) {

            Intent intent=new Intent(WorkHomeActivity.this, ImageUploadActivity.class);
            startActivity(intent);
            // Handle the camera action

        } else if (id == R.id.reminder) {
            Intent intent=new Intent(WorkHomeActivity.this,ServiceActivity.class);
            startActivity(intent);

        } else if (id == R.id.listworkers) {
            //make workers list doc or pdf and link here
            //Intent intent=new Intent(WorkHomeActivity.this,ServiceActivity.class);
            //startActivity(intent);



        } else if (id == R.id.schedule) {
            //the sceduled dates for service to be displayed herer with scheduled minor checkup
            //add a floatingmeu for worker to schedule services and checkup for he buses wth the bus number

            //make an tab for emergencies...
            //make tab to view job cards.......

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //make a function for card view selection here
    //public void




}
