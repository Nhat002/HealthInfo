package com.example.nhat0.app3002.task;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.nhat0.app3002.LoginActivity;
import com.example.nhat0.app3002.adapter.HttpRequestHandler;
import com.example.nhat0.app3002.controller.LoginController;
import com.example.nhat0.app3002.controller.MainController;
import com.example.nhat0.app3002.entity.AppPreferences;
import com.example.nhat0.app3002.entity.HealthcareInformation;
import com.example.nhat0.app3002.entity.WeatherForecastInformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by nhat0 on 28/3/2016.
 */
public class LoadWeatherDataTask extends AsyncTask<String,Void,String>{
    MainController mainController = MainController.getInstance();
    @Override
    protected String doInBackground(String... params){

        mainController.addCategories();
        HttpRequestHandler httpRequestHandler = HttpRequestHandler.getInstance();
        String result = null;
        try {
            result = httpRequestHandler.GET(params[0],"w");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result){
        LoginController loginController = LoginController.getInstance();
        Log.d("data load",result);
        loginController.getActivityHandler().obtainMessage(LoginActivity.MESSAGE_START_MAIN_ACTIVITY).sendToTarget();

    }
}
