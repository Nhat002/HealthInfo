package com.example.nhat0.app3002;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.nhat0.app3002.controller.MainController;
import com.example.nhat0.app3002.entity.AlarmReceiver;
import com.example.nhat0.app3002.entity.AppNotification;
import com.example.nhat0.app3002.entity.AppPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by nhat0 on 29/3/2016.
 */
public class CreateNotificationActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{
    private EditText title;
    private EditText msg;
    private Button setTimeButton;
    private Button createNotification;
    private Calendar cal = null;
    private Context context = this;
    private Spinner notificationTypeDropdown;
    private TextView timeSelect;
    private RadioButton daily,hourly;
    private EditText intervalField;
    private String type;
    String[] arrayType;
    private boolean isDaily = true;
    private int interval = 1;
    private TextView intervalUnit;
    private MainController mainController = MainController.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_create_notification);
        arrayType = getResources().getStringArray(R.array.notification_type);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cal = Calendar.getInstance();
        title = (EditText) findViewById(R.id.noti_title_field);
        msg = (EditText) findViewById(R.id.noti_msg_field);
        timeSelect = (TextView) findViewById(R.id.time_select);
        daily = (RadioButton) findViewById(R.id.dailyNoti);
        daily.setChecked(true);
        daily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    hourly.setChecked(false);
                    isDaily = true;
                    intervalUnit.setText("day(s)");
                }
            }
        });
        intervalUnit = (TextView) findViewById(R.id.interval_unit);
        hourly = (RadioButton) findViewById(R.id.hourlyNoti);
        hourly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    daily.setChecked(false);
                    isDaily=false;
                    intervalUnit.setText("hour(s)");
                }
            }
        });

        intervalField = (EditText) findViewById(R.id.interval);
        setTimeButton = (Button) findViewById(R.id.btn_set_time);
        setTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog tp1 = new TimePickerDialog(context, (TimePickerDialog.OnTimeSetListener) context, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);
                tp1.show();
            }
        });

        notificationTypeDropdown = (Spinner) findViewById(R.id.notification_type_dropdown);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.notification_type, android.R.layout.simple_spinner_item);

// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        notificationTypeDropdown.setAdapter(adapter);
        notificationTypeDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = arrayType[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        createNotification = (Button) findViewById(R.id.btn_create_noti);
        createNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppNotification appNotification = new AppNotification(title.getText().toString().trim(),msg.getText().toString().trim(),
                        mainController.getUsername(),mainController.db.getAllNotification().size()+1);
                mainController.db.createNotification(appNotification);
                mainController.getNotificationListAdapter().addNoti(appNotification);
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR));
                calendar.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
                calendar.set(Calendar.SECOND, 0);
                Intent intent1 = new Intent(mainController.getMainActivityContext(), AlarmReceiver.class);
                intent1.putExtra(AppPreferences.NOTIFICATION_TITLE,appNotification.getTitle());
                intent1.putExtra(AppPreferences.NOTIFICATION_CONTENT,appNotification.getMsg());
                PendingIntent pendingIntent = PendingIntent.getBroadcast(mainController.getMainActivityContext(), 0,intent1, PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager am = (AlarmManager) mainController.getMainActivityContext().getSystemService(mainController.getMainActivityContext().ALARM_SERVICE);
                if(isDaily) {
                    am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), interval * AlarmManager.INTERVAL_DAY, pendingIntent);
                }
                else{
                    am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), interval * AlarmManager.INTERVAL_HOUR, pendingIntent);
                }

                Intent intent2 = new Intent(context,NotificationActivity.class);
                startActivity(intent2);
            }

        });
    }
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        cal.set(Calendar.MINUTE, minute);
        timeSelect.setText("Time selected: " + new SimpleDateFormat("HH:mm").format(cal.getTime()));
    }
}
