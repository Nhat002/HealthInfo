package com.example.nhat0.app3002.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.nhat0.app3002.LoginActivity;
import com.example.nhat0.app3002.entity.AppPreferences;

/**
 * Created by nhat0 on 16/3/2016.
 */
public class LoginController {
    private static final String NUM_ACC = "num_acc";
    private static final int MAX_NUM_ACC = 3;
    private String username = "user";
    private boolean isLogin = false;
    private SharedPreferences sp;
    private int numOfAccAvailable = 3;
    private int userOrder = -1;
    public Context context;
    public LoginActivity loginAcitivityContext;
    private Handler mHandler= null;

    public boolean isLogin() {
        return isLogin;
    }

    private void loadNumOfAccount(){
        sp = context.getSharedPreferences(AppPreferences.NUM_ACC_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if(sp.getInt(NUM_ACC,-1) == -1){
            editor.putInt(NUM_ACC, 3);
            editor.commit();
        }
        else if(sp.getInt(NUM_ACC,-1) == 0){
            Toast.makeText(context.getApplicationContext(),"no more account can be created",Toast.LENGTH_SHORT).show();
        }
        else{
            numOfAccAvailable = sp.getInt(NUM_ACC,-1);
        }
        Log.d("Num avail", String.valueOf(numOfAccAvailable));
    }


    public boolean validUsername(String user) {
        sp = context.getSharedPreferences(AppPreferences.LOGIN_FILE_NAME, Context.MODE_PRIVATE);
        for(int i =0; i < MAX_NUM_ACC - numOfAccAvailable; ++i){
            if(user.equals(sp.getString(String.format("Acc%d",i+1),null))){
                userOrder = i+1;
                username =user;
                return true;
            }
        }
        return false;
    }

    public boolean validPassword(String pass) {
        if(userOrder == -1){
            return false;
        }
        sp = context.getSharedPreferences(AppPreferences.PASSWORD_FILE_NAME, Context.MODE_PRIVATE);
        if(pass.equals(sp.getString(String.format("Pass%d",userOrder),null))){
            MainController mainController = MainController.getInstance();
            mainController.setUsername(username);
            SharedPreferences otherSp = context.getSharedPreferences(AppPreferences.REMEMBER_LOGIN_FILE,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor= otherSp.edit();
            editor.putBoolean(AppPreferences.REMEMBER_LOGIN, true);
            editor.commit();
            return true;
        }
        return false;
    }

    public void acceptActivityHandler(Handler mHandler) {
        this.mHandler = mHandler;
    }

    public Handler getActivityHandler() {
        return mHandler;
    }

    static class LoginControllerHolder{
        static LoginController instance = new LoginController();
    }

    public static LoginController getInstance() { return LoginControllerHolder.instance; }

    public void checkLogin(){
        loadNumOfAccount();
        sp = context.getSharedPreferences(AppPreferences.REMEMBER_LOGIN_FILE, Context.MODE_PRIVATE);
        if(sp.getBoolean(AppPreferences.REMEMBER_LOGIN,false)){
           isLogin = true;
        }
        else{
            isLogin = false;
        }
        return;
    }

    public boolean register(String newUsername, String newPassword){
        if(numOfAccAvailable < 1){
            return false;
        }
        sp = context.getSharedPreferences(AppPreferences.LOGIN_FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sp.edit();
        editor.putString(String.format("Acc%d",4-numOfAccAvailable),newUsername);
        editor.commit();
        /////////////////////////////
        sp = context.getSharedPreferences(AppPreferences.PASSWORD_FILE_NAME, Context.MODE_PRIVATE);
        editor =sp.edit();
        editor.putString(String.format("Pass%d", 4 - numOfAccAvailable), newPassword);
        editor.commit();
        numOfAccAvailable--;
        /////////////////////////////
        sp = context.getSharedPreferences(AppPreferences.NUM_ACC_FILE, Context.MODE_PRIVATE);
        editor = sp.edit();
        editor.putInt(NUM_ACC, numOfAccAvailable);
        editor.commit();


        return true;
    }
}
