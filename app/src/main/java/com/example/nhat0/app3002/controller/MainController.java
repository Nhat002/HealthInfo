package com.example.nhat0.app3002.controller;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.view.Display;

import com.example.nhat0.app3002.MainActivity;
import com.example.nhat0.app3002.adapter.DatabaseHandler;
import com.example.nhat0.app3002.adapter.InformationListAdapter;
import com.example.nhat0.app3002.adapter.NotificationListAdapter;
import com.example.nhat0.app3002.entity.AppNotification;
import com.example.nhat0.app3002.entity.AppPreferences;
import com.example.nhat0.app3002.entity.Category;
import com.example.nhat0.app3002.entity.HealthInformation;
import com.example.nhat0.app3002.entity.HealthcareInformation;
import com.example.nhat0.app3002.entity.WeatherForecastInformation;

import java.util.ArrayList;

/**
 * Created by nhat0 on 7/3/2016.
 */
public class MainController {
    /*Attributes*/
    public static int screenWidth;
    public static int screenHeight;
    private Context context;
    private Context mainActivityContext;
    private int height;
    private int width;
    private ArrayList<HealthInformation> data = new ArrayList<HealthInformation>();
    private ArrayList<HealthInformation> removedData = new ArrayList<HealthInformation>();
    private ArrayList<Category> categories = new ArrayList<>();
    private InformationListAdapter informationListAdapter = new InformationListAdapter(context,data);
    private NotificationListAdapter notificationListAdapter;
    private String username = null;
    public int getHeight() {
        return height;
    }


    public int getWidth() {
        return width;
    }

    public Context getContext() {
        return context;
    }

    public String getUsername() {
        if(username == null){
            /*SharedPreferences sp = context.getApplicationContext().getSharedPreferences(AppPreferences.LOGIN_FILE_NAME,Context.MODE_PRIVATE);
            username = sp.getString();*/
            username = "User";
        }
        return username;
    }

    public DatabaseHandler db;

    public void setUsername(String username) {
        this.username = username;
    }

    public void addData(HealthInformation[] data) {
        for(int i =0; i < data.length; ++i){
            this.data.add(data[i]);
        }
    }
    public void createInformationListAdapter(){
        informationListAdapter = new InformationListAdapter(mainActivityContext,this.data);
    }
    public ArrayList<HealthInformation> getData() {
        return data;
    }

    public InformationListAdapter getInformationListAdapter() {
        return informationListAdapter;
    }

    public void addCategories() {
        for(int i=0; i < AppPreferences.allCategory.length;++i){
            Category newCategory = new Category(AppPreferences.allCategory[i]);
            categories.add(newCategory);
        }
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public ArrayList<HealthInformation> getRemovedData() {
        return removedData;
    }

    public Context getMainActivityContext() {
        return mainActivityContext;
    }

    public void setMainActivityContext(Context mainActivityContext) {
        this.mainActivityContext = mainActivityContext;
    }

    public void clearData() {
        this.data.clear();
    }

    public NotificationListAdapter getNotificationListAdapter() {
        return notificationListAdapter;
    }

    public void setNotificationListAdapter(NotificationListAdapter notificationListAdapter) {
        this.notificationListAdapter = notificationListAdapter;
    }


    /*Implementation for Singleton Class*/
    static class MainControllerHolder{
        static MainController instance = new MainController();
    }

    public static MainController getInstance(){
        return MainControllerHolder.instance;
    }

    /*Methods*/
    public void getScreenDimensions(MainActivity mainActivity){
        Display display = mainActivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
    }

    public void setContext(Context context){
        this.context = context;
    }
}
