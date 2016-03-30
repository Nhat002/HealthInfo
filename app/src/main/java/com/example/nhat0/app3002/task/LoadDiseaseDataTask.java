package com.example.nhat0.app3002.task;

import android.os.AsyncTask;
import android.util.Log;

import com.example.nhat0.app3002.LoginActivity;
import com.example.nhat0.app3002.adapter.HttpRequestHandler;
import com.example.nhat0.app3002.controller.LoginController;
import com.example.nhat0.app3002.controller.MainController;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by nhat0 on 29/3/2016.
 */
public class LoadDiseaseDataTask extends AsyncTask<String,Void,String> {
    MainController mainController = MainController.getInstance();
    @Override
    protected String doInBackground(String... params) {
        mainController.db.clearTableOnProgramStart();
        mainController.clearData();
        mainController.addCategories();
        HttpRequestHandler httpRequestHandler = HttpRequestHandler.getInstance();
        String result = null;
        try {
            result = httpRequestHandler.GET(params[0],"d");
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
        Log.d("data load", result);
        loginController.getActivityHandler().obtainMessage(LoginActivity.MESSAGE_LOAD_WEATHER_DATA).sendToTarget();

    }
}
