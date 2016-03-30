package com.example.nhat0.app3002.adapter;

import android.app.Notification;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.nhat0.app3002.entity.AppNotification;
import com.example.nhat0.app3002.entity.DiseaseInformation;
import com.example.nhat0.app3002.entity.HealthcareInformation;
import com.example.nhat0.app3002.entity.WeatherForecastInformation;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by nhat0 on 13/3/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String LOG = "DatabaseHelper";
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "healthInformation";

    // Contacts table name
    private static final String TABLE_HEALTHCARE_INFORMATION = "healthcareInformation";
    private static final String TABLE_DISEASE_INFORMATION = "diseaseInformation";
    private static final String TABLE_WEATHERFORECAST_INFORMATION="weatherForecastInformation";
    private static final String TABLE_NOTIFICATION = "notifications";
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_URGENT = "urgent";
    private static final String KEY_UPDATE_TIME = "update_time";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_WEATHER_TYPE = "weather_type";
    private static final String KEY_WEATHER_TIME = "weather_time";
    private static final String KEY_DISEASE_TYPE = "disease_type";
    private static final String KEY_MSG = "message";
    private static final String KEY_ONLINE_ID = "online_id";
    private static final String KEY_OWNED_BY = "owned_by";
    private static final String KEY_NOTIFICATION_ID = "notification_id";
    // Table Create Statements
    // HealthcareInformation table create statement
    private static final String CREATE_TABLE_HEALTHCARE_INFORMATION = "CREATE TABLE "
            + TABLE_HEALTHCARE_INFORMATION + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE
            + " TEXT," + KEY_CONTENT + " TEXT," + KEY_URGENT + " INTEGER," + KEY_UPDATE_TIME + " INTEGER,"
            + KEY_CATEGORY + " TEXT)";
    private static final String CREATE_TABLE_DESEASE_INFORMATION = "CREATE TABLE "
            + TABLE_DISEASE_INFORMATION + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE
            + " TEXT," + KEY_CONTENT + " TEXT," + KEY_URGENT + " INTEGER," + KEY_UPDATE_TIME + " INTEGER,"
            + KEY_DISEASE_TYPE + " TEXT)";
    private static final String CREATE_TABLE_WEATHERFORECAST_INFORMATION = "CREATE TABLE "
            + TABLE_WEATHERFORECAST_INFORMATION + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE
            + " TEXT," + KEY_CONTENT + " TEXT," + KEY_URGENT + " INTEGER," + KEY_UPDATE_TIME + " INTEGER,"
            + KEY_ONLINE_ID + " INTEGER,"
            + KEY_WEATHER_TYPE + " TEXT," + KEY_WEATHER_TIME + " INTEGER)";

    private static final String CREATE_TABLE_NOTIFICATION = "CREATE TABLE "
            + TABLE_NOTIFICATION + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_TITLE + " TEXT," + KEY_MSG + " TEXT,"
            + KEY_NOTIFICATION_ID + " INTEGER,"
            + KEY_OWNED_BY + " TEXT)";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_HEALTHCARE_INFORMATION);
        db.execSQL(CREATE_TABLE_DESEASE_INFORMATION);
        db.execSQL(CREATE_TABLE_WEATHERFORECAST_INFORMATION);
        db.execSQL(CREATE_TABLE_NOTIFICATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HEALTHCARE_INFORMATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DISEASE_INFORMATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEATHERFORECAST_INFORMATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATION);
        // create new tables
        onCreate(db);
    }

    public void clearTableOnProgramStart(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WEATHERFORECAST_INFORMATION, null, null);
        db.delete(TABLE_HEALTHCARE_INFORMATION, null, null);
        db.delete(TABLE_DISEASE_INFORMATION, null, null);
    }
    public long createHealthcareInformation(HealthcareInformation info){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, info.getTitle());
        values.put(KEY_CONTENT, info.getContent());
        values.put(KEY_URGENT, info.getUrgentPriority());
        values.put(KEY_UPDATE_TIME, info.getUpdateTime().getTimeInMillis());
        values.put(KEY_CATEGORY, info.getCategory());
        long info_id = db.insert(TABLE_HEALTHCARE_INFORMATION,null,values);
        info.setId(info_id);
        return info_id;
    }

    public HealthcareInformation getHealthcareInformation(long info_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_HEALTHCARE_INFORMATION + " WHERE "
                + KEY_ID + " = " + info_id;

        Cursor c = db.rawQuery(selectQuery, null);
        if(c != null){
            c.moveToFirst();
        }
        else{
            return null;
        }
        HealthcareInformation info = new HealthcareInformation();
        info.setId(c.getLong(c.getColumnIndex(KEY_ID)));
        info.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
        info.setContent(c.getString(c.getColumnIndex(KEY_CONTENT)));
        info.setUrgentPriority(c.getInt(c.getColumnIndex(KEY_URGENT)));
        info.setUpdateTime(c.getLong(c.getColumnIndex(KEY_UPDATE_TIME)));
        info.setCategory(c.getString(c.getColumnIndex(KEY_CATEGORY)));
        return info;
    }

    public long createDiseaseInformation(DiseaseInformation info){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, info.getTitle());
        values.put(KEY_CONTENT, info.getContent());
        values.put(KEY_URGENT, info.getUrgentPriority());
        values.put(KEY_UPDATE_TIME, info.getUpdateTime().getTimeInMillis());
        values.put(KEY_DISEASE_TYPE, info.getDiseaseType());
        long info_id = db.insert(TABLE_DISEASE_INFORMATION,null,values);
        Log.d("inserted id", String.valueOf(info_id));
        info.setId(info_id);
        return info_id;
    }

    public DiseaseInformation getDiseaseInformation(long info_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_DISEASE_INFORMATION + " WHERE "
                + KEY_ID + " = " + info_id;

        Cursor c = db.rawQuery(selectQuery, null);
        if(c != null){
            c.moveToFirst();
        }
        else{
            return null;
        }
        DiseaseInformation info = new DiseaseInformation();
        info.setId(c.getLong(c.getColumnIndex(KEY_ID)));
        info.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
        info.setContent(c.getString(c.getColumnIndex(KEY_CONTENT)));
        info.setUrgentPriority(c.getInt(c.getColumnIndex(KEY_URGENT)));
        info.setUpdateTime(c.getLong(c.getColumnIndex(KEY_UPDATE_TIME)));
        info.setDiseaseType(c.getString(c.getColumnIndex(KEY_DISEASE_TYPE)));
        return info;
    }

    public long createWeatherForecastInformation(WeatherForecastInformation info){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, info.getTitle());
        values.put(KEY_CONTENT, info.getContent());
        values.put(KEY_URGENT, info.getUrgentPriority());
        values.put(KEY_UPDATE_TIME, info.getUpdateTime().getTimeInMillis());
        values.put(KEY_WEATHER_TYPE, info.getWeatherType());
        values.put(KEY_WEATHER_TIME, info.getWeatherTime().getTimeInMillis());
        values.put(KEY_ONLINE_ID, info.getOnlineId());
        long info_id = db.insert(TABLE_WEATHERFORECAST_INFORMATION,null,values);
        info.setId(info_id);
        Log.d("inserted id", String.valueOf(info_id));
        return info_id;
    }

    public WeatherForecastInformation getWeatherForecastInformation(long info_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_WEATHERFORECAST_INFORMATION + " WHERE "
                + KEY_ID + " = " + info_id;

        Cursor c = db.rawQuery(selectQuery, null);
        if(c != null){
            c.moveToFirst();
        }
        else{
            return null;
        }
        WeatherForecastInformation info = new WeatherForecastInformation();
        info.setId(c.getLong(c.getColumnIndex(KEY_ID)));
        info.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
        info.setContent(c.getString(c.getColumnIndex(KEY_CONTENT)));
        info.setUrgentPriority(c.getInt(c.getColumnIndex(KEY_URGENT)));
        info.setUpdateTime(c.getLong(c.getColumnIndex(KEY_UPDATE_TIME)));
        info.setWeatherType(c.getString(c.getColumnIndex(KEY_WEATHER_TYPE)));
        info.setWeatherTime(c.getLong(c.getColumnIndex(KEY_WEATHER_TIME)));
        info.setOnlineId(c.getString(c.getColumnIndex(KEY_ONLINE_ID)));
        return info;
    }

    public long createNotification(AppNotification noti){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, noti.getTitle());
        values.put(KEY_MSG, noti.getMsg());
        values.put(KEY_OWNED_BY, noti.getOwnedBy());
        values.put(KEY_NOTIFICATION_ID, noti.getNotificationID());
        long id = db.insert(TABLE_NOTIFICATION,null,values);
        noti.setId(id);
        return id;

    }

    public ArrayList<AppNotification> getAllNotification(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<AppNotification> notis = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NOTIFICATION;

        Cursor c = db.rawQuery(selectQuery, null);
        if(c!=null){
            c.moveToFirst();
            if(c.getCount() == 0){
                return notis;
            }
            do{
                String title = c.getString(c.getColumnIndex(KEY_TITLE));
                String msg  = c.getString(c.getColumnIndex(KEY_MSG));
                String ownedBy = c.getString(c.getColumnIndex(KEY_OWNED_BY));
                int notiID = c.getInt(c.getColumnIndex(KEY_NOTIFICATION_ID));
                AppNotification noti  = new AppNotification(title,msg,ownedBy,notiID);
                notis.add(noti);
            }while(c.moveToNext());
        }
        return notis;
    }
    public void deleteHealthcareInformation(long info_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HEALTHCARE_INFORMATION, KEY_ID + " = ?",
                new String[]{String.valueOf(info_id)});
    }

    public void deleteDiseaseInformation(long info_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DISEASE_INFORMATION, KEY_ID + " = ?",
                new String[] {String.valueOf(info_id)});
    }

    public void deleteWeatherForecastInformation(long info_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WEATHERFORECAST_INFORMATION, KEY_ID + " = ?",
                new String[] {String.valueOf(info_id)});
    }

}
