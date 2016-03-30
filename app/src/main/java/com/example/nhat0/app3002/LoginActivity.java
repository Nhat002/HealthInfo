package com.example.nhat0.app3002;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nhat0.app3002.adapter.DatabaseHandler;
import com.example.nhat0.app3002.controller.LoginController;
import com.example.nhat0.app3002.controller.MainController;
import com.example.nhat0.app3002.entity.AppPreferences;
import com.example.nhat0.app3002.task.LoadDiseaseDataTask;
import com.example.nhat0.app3002.task.LoadWeatherDataTask;

/**
 * Created by nhat0 on 16/3/2016.
 */
public class LoginActivity extends AppCompatActivity {
    public static final int MESSAGE_START_MAIN_ACTIVITY = 1;
    public static final int MESSAGE_LOAD_WEATHER_DATA = 2;
    private EditText username;
    private EditText password;
    private Button loginButton;
    private LoginController loginController = LoginController.getInstance();
    private MainController mainController = MainController.getInstance();
    private Button registerButton;
    private Context context = this;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.name_field);
        password = (EditText) findViewById(R.id.password_field);
        loginButton = (Button) findViewById(R.id.btn_login);
        registerButton = (Button) findViewById(R.id.btn_register);
        //set up database
        mainController.setContext(getApplicationContext());
        mainController.db = new DatabaseHandler(getApplicationContext());

        //set up login controller
        loginController.context = this.getApplicationContext();
        loginController.loginAcitivityContext = this;
        loginController.acceptActivityHandler(mHandler);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                if(loginController.validUsername(user)){
                    if(loginController.validPassword(pass)){
                        new LoadDiseaseDataTask().execute(AppPreferences.DiseaseDataAPI);
                        finish();
                    }
                    else{
                        Toast.makeText(getBaseContext(),"invalid password",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getBaseContext(),"invalid user",Toast.LENGTH_LONG).show();
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,RegisterActivity.class);
                startActivityForResult(intent, AppPreferences.REQUEST_REGISTER);
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        loginController.checkLogin();
        if(loginController.isLogin()){
            new LoadDiseaseDataTask().execute(AppPreferences.DiseaseDataAPI);
            finish();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == AppPreferences.REQUEST_REGISTER){
            if(resultCode == RESULT_OK){
                Toast.makeText(this,"register successful",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,"register fail",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private final Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            if(msg.what == MESSAGE_START_MAIN_ACTIVITY){
                Intent intent = new Intent("android.intent.action.PORTAL");
                startActivity(intent);
            }
            else if(msg.what == MESSAGE_LOAD_WEATHER_DATA){
                new LoadWeatherDataTask().execute(AppPreferences.WeatherDataAPI);
            }

        }
    };
}
