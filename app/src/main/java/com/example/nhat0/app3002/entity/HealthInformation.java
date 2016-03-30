package com.example.nhat0.app3002.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by nhat0 on 7/3/2016.
 */
public class HealthInformation implements Comparable{
    private long id;
    private String title;
    private String content;
    private int urgentPriority;
    private Calendar updateTime = Calendar.getInstance();
    private String onlineId;
    public HealthInformation( String title, String content, int urgentPriority){
        this.id = -1;
        updateTime = Calendar.getInstance();
        this.title = title;
        this.content = content;
        this.urgentPriority = urgentPriority;
        this.onlineId = null;
    }

    public HealthInformation() {

    }

    public String getFormatUpdateTime() {
        return new SimpleDateFormat("MMM d HH:mm").format(updateTime.getTime());
    }

    public void setUpdateTime(long updateTimeLong) {
        this.updateTime = Calendar.getInstance();
        this.updateTime.setTimeInMillis(updateTimeLong);
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getUrgentPriority() {
        return urgentPriority;
    }

    public void setUrgentPriority(int urgentPriority) {
        this.urgentPriority = urgentPriority;
    }

    @Override
    public int compareTo(Object another) {
        HealthInformation hObj = (HealthInformation) another;
        long thisTime = this.updateTime.getTimeInMillis();
        long anotherTime = hObj.getUpdateTime().getTimeInMillis();
        if(thisTime == anotherTime){
            return 0;
        }
        else if(thisTime > anotherTime){
            return 1;
        }
        else{
            return -1;
        }
    }

    public Calendar getUpdateTime() {
        return updateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOnlineId() {
        return onlineId;
    }

    public void setOnlineId(String onlineId) {
        this.onlineId = onlineId;
    }
}
