package com.example.nhat0.app3002.entity;

import org.json.JSONArray;

/**
 * Created by nhat0 on 10/3/2016.
 */
public class AppPreferences {

    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String[] FRAGMENT_TITLE_LIST= {"Information","Filter"};
    public static final int PAGE_COUNT = 2;
    public static final String NUM_ACC_FILE = "com.example.nhat0.app3002.num_acc";
    public static final String PASSWORD_FILE_NAME = "com.example.nhat0.app3002.password_file";
    public static final String REMEMBER_LOGIN_FILE = "com.example.nhat0.app3002.remember_login";
    public static final int REQUEST_REGISTER = 1;
    public static final int RESULT_NOT_OK = 2;
    public static final String WEATHER_TYPE = "weatherType";
    public static final String WEATHER_TIME = "weatherTime";
    public static final String UPDATE_TIME = "updateTime";
    public static final String ID = "_id";
    public static final String DISEASE_TITLE = "diseaseTitle";
    public static final String DISEASE_CONTENT = "diseaseContent";
    public static final String DISEASE_TYPE = "diseaseType";
    public static final String NOTIFICATION_TITLE = "title";
    public static final String NOTIFICATION_CONTENT = "content";
    public static String LOGIN_FILE_NAME = "com.example.nhat0.app3002.login_file";
    public static String REMEMBER_LOGIN = "remember_login";
    public static String[] allCategory = {"Weather Forecast","Disease","Healthcare Information","Warning","Advisory","Guide"};
    public static String[] healthcareCategory = {"Warning","Advisory","Guide"};
    public static String WeatherDataAPI = "http://54.169.116.128/weather";

    public static final String DATA = "data_single_view" ;
    public static final String DATA_ID = "id";
    public static final String DATA_TYPE = "type";
    public static String DiseaseDataAPI = "http://54.169.116.128/disease";

}
