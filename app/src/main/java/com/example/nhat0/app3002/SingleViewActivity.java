package com.example.nhat0.app3002;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhat0.app3002.controller.MainController;
import com.example.nhat0.app3002.entity.AppPreferences;
import com.example.nhat0.app3002.entity.HealthInformation;

import org.w3c.dom.Text;

/**
 * Created by nhat0 on 17/3/2016.
 */
public class SingleViewActivity extends AppCompatActivity {
    public static final String TAG = "SingleViewActivity";
    private MainController mainController;
    private ImageView imageInfoView;
    private TextView titleViewField;
    private TextView contentViewField;
    private TextView updateTimeViewField;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_view);
        mainController = MainController.getInstance();
        imageInfoView = (ImageView) findViewById(R.id.image_single_view);
        titleViewField = (TextView) findViewById(R.id.title_view_field);
        contentViewField = (TextView) findViewById(R.id.content_view_field);
        updateTimeViewField = (TextView) findViewById(R.id.update_time_view_field);

        Intent intent = getIntent();
        long id = intent.getLongExtra(AppPreferences.DATA_ID, -1L);
        Log.d("ID",String.valueOf(id));
        String type = intent.getStringExtra(AppPreferences.DATA_TYPE);
        HealthInformation info = null;
        if(type.equals("h")){
            info = mainController.db.getHealthcareInformation(id);
            imageInfoView.setImageResource(R.mipmap.ic_icon_h);
        }else if(type.equals("w")){
            info = mainController.db.getWeatherForecastInformation(id);
            imageInfoView.setImageResource(R.mipmap.ic_icon_w);
        }else if(type.equals("d")){
            info = mainController.db.getDiseaseInformation(id);
            imageInfoView.setImageResource(R.mipmap.ic_icon_d);
        }

        if(info != null) {
            titleViewField.setText(info.getTitle());
            contentViewField.setText(info.getContent());
            updateTimeViewField.setText(info.getFormatUpdateTime());
        }
    }
}
