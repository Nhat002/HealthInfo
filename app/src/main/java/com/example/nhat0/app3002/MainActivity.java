package com.example.nhat0.app3002;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.nhat0.app3002.adapter.MainTabAdapter;
import com.example.nhat0.app3002.controller.LoginController;
import com.example.nhat0.app3002.controller.MainController;
import com.example.nhat0.app3002.entity.AppPreferences;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /*Attributes*/
    /** Main Controller instance*/
    private MainController mainController = MainController.getInstance();
    /** viewPager the ViewPager*/
    private ViewPager viewPager;
    /** tabLayout the TabLayout*/
    private TabLayout tabLayout;
    /** username the TextView*/
    private TextView username;
    /*Methods*/
    /**
     * Return nothing, method to create the activity
     * @param savedInstanceState a bundle that stored the previous state of this activity if available
     * @return void nothing return
     * **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*set up toolbars*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mainController.setMainActivityContext(this);
        mainController.createInformationListAdapter();
        /*set up Floating Action Button*/
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        username = (TextView) navigationView.getHeaderView(0).findViewById(R.id.username_label);
        username.setText(mainController.getUsername());
        navigationView.setNavigationItemSelectedListener(this);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MainTabAdapter(getSupportFragmentManager(), getApplicationContext()));

        tabLayout = (TabLayout) findViewById(R.id.fixed_tabs);
        tabLayout.setupWithViewPager(viewPager);
        TextView tab1 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab_view,null);
        tab1.setText(AppPreferences.FRAGMENT_TITLE_LIST[0]);
        tab1.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tab1);
        TextView tab2 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab_view,null);
        tab2.setText(AppPreferences.FRAGMENT_TITLE_LIST[1]);
        tab2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_filter, 0);
        tabLayout.getTabAt(1).setCustomView(tab2);
    }


    @Override
    public void onStart(){
        super.onStart();
    }
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id == R.id.logout_label){
            SharedPreferences otherSp = LoginController.getInstance().context.getSharedPreferences(AppPreferences.REMEMBER_LOGIN_FILE, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor= otherSp.edit();
            editor.putBoolean(AppPreferences.REMEMBER_LOGIN, false);
            editor.commit();
            finish();
        }else if(id == R.id.notification_item){
            Intent intent= new Intent(this, NotificationActivity.class);
            startActivity(intent);
            finish();
        }
        else if(id == R.id.home_item){

        }
       /* if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
