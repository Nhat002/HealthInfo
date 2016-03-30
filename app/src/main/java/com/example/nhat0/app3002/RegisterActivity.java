package com.example.nhat0.app3002;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nhat0.app3002.controller.LoginController;
import com.example.nhat0.app3002.entity.AppPreferences;

/**
 * Created by nhat0 on 27/3/2016.
 */
public class RegisterActivity extends AppCompatActivity {
    private EditText newUsernameField;
    private EditText newPasswordField;
    private Button submitRegisterButton;
    private TextView usernameValid;
    private LoginController loginController = LoginController.getInstance();
    private boolean isUsernameValid = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        newUsernameField = (EditText) findViewById(R.id.new_username_field);
        newPasswordField = (EditText) findViewById(R.id.new_password_field);
        usernameValid = (TextView) findViewById(R.id.username_valid_used);
        newUsernameField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    usernameValid.setText("");
                }else{
                    String username = newUsernameField.getText().toString().trim();
                    if(loginController.validUsername(username)){
                        usernameValid.setBackgroundColor(getResources().getColor(R.color.colorRed));
                        usernameValid.setText("username is used");
                        isUsernameValid = false;
                    }else{
                        usernameValid.setBackgroundColor(getResources().getColor(R.color.colorNeon));
                        usernameValid.setText("username is free to use");
                        isUsernameValid = true;
                    }
                }
            }
        });
        submitRegisterButton = (Button) findViewById(R.id.btn_submit_register);
        submitRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isUsernameValid){
                    if(loginController.register(newUsernameField.getText().toString().trim(),newPasswordField.getText().toString().trim())){
                        if(getParent() == null){
                            setResult(RESULT_OK);
                        }
                        else{
                            getParent().setResult(RESULT_OK);
                        }
                    }
                    else{
                        if(getParent() == null){
                            setResult(AppPreferences.RESULT_NOT_OK);
                        }
                        else{
                            getParent().setResult(AppPreferences.RESULT_NOT_OK);
                        }
                    }
                    finish();
                }

            }
        });
    }
}
