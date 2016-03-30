package com.example.nhat0.app3002.adapter;

import android.app.DownloadManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.nhat0.app3002.controller.LoginController;
import com.example.nhat0.app3002.controller.MainController;
import com.example.nhat0.app3002.entity.AppPreferences;
import com.example.nhat0.app3002.entity.DiseaseInformation;
import com.example.nhat0.app3002.entity.HealthcareInformation;
import com.example.nhat0.app3002.entity.WeatherForecastInformation;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by nhat0 on 28/3/2016.
 */
public class HttpRequestHandler {
    private MainController mainController = MainController.getInstance();
    static class HttpRequestHandlerHolder {
        static HttpRequestHandler instance = new HttpRequestHandler();
    }

    public static HttpRequestHandler getInstance(){
        return HttpRequestHandlerHolder.instance;
    }

    public String GET(String stringURL, String dataType) throws IOException, JSONException {
        LoginController loginController =LoginController.getInstance();
        if(!isConnected(loginController.loginAcitivityContext)){
            return "Not connected";
        }
        URL url = null;
        try {
            url = new URL(stringURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStream in = null;
        if (url != null) {
            try {
                HttpURLConnection urlConnection =
                        (HttpURLConnection)url.openConnection();
                in = new BufferedInputStream(
                        urlConnection.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String result = readStream(in);
        if(dataType.equals("w")) {
            handleWeatherJSONData(result);
        }
        else if (dataType.equals("d")) {
            handleDiseaseJSONData(result);
        }
        return result;

    }

    private void handleWeatherJSONData(String data) throws JSONException {
        JSONArray jsonArray = null;

        jsonArray = new JSONArray(data);
        if(data == null){
            return;
        }
        WeatherForecastInformation[] wData = new WeatherForecastInformation[jsonArray.length()];
        for(int i = 0; i < jsonArray.length(); ++i) {
            JSONObject jsonObject = null;
            jsonObject = jsonArray.getJSONObject(i);
            wData[i] = new WeatherForecastInformation();
            wData[i].setWeatherType(jsonObject.getString(AppPreferences.WEATHER_TYPE));
            wData[i].setWeatherTime(Long.parseLong(jsonObject.getString(AppPreferences.WEATHER_TIME)) * 1000);
            wData[i].setUpdateTime(Long.parseLong(jsonObject.getString(AppPreferences.UPDATE_TIME)));
            Calendar c = wData[i].getWeatherTime();
            wData[i].setTitle("Weather forecast at " + new SimpleDateFormat("HH:mm MMM d").format(c.getTime()));
            wData[i].setContent(wData[i].getWeatherType());
            wData[i].setOnlineId(jsonObject.getString(AppPreferences.ID));
            mainController.db.createWeatherForecastInformation(wData[i]);
        }
        mainController.addData(wData);
    }

    private void handleDiseaseJSONData(String data) throws JSONException {
        JSONArray jsonArray = null;

        jsonArray = new JSONArray(data);
        if(data == null){
            return;
        }

        DiseaseInformation[] dData = new DiseaseInformation[jsonArray.length()];
        for(int i = 0; i < jsonArray.length(); ++i){
            JSONObject jsonObject = null;
            jsonObject = jsonArray.getJSONObject(i);
            dData[i] = new DiseaseInformation();
            dData[i].setUpdateTime(Long.parseLong(jsonObject.getString(AppPreferences.UPDATE_TIME)));
            dData[i].setDiseaseType(jsonObject.getString(AppPreferences.DISEASE_TYPE));
            dData[i].setTitle(dData[i].getDiseaseType() + " " +jsonObject.getString(AppPreferences.DISEASE_TITLE));
            dData[i].setContent(jsonObject.getString(AppPreferences.DISEASE_CONTENT));

            dData[i].setUrgentPriority(i+1);
            dData[i].setOnlineId(jsonObject.getString(AppPreferences.ID));
            mainController.db.createDiseaseInformation(dData[i]);
        }
        mainController.addData(dData);
    }
    private String readStream(InputStream in) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(in));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null) {
            result += line;
        }
        in.close();
        return result;
    }

    // check network connection
    public boolean isConnected(Context context){
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
}
