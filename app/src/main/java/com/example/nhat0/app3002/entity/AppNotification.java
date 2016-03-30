package com.example.nhat0.app3002.entity;

/**
 * Created by nhat0 on 13/3/2016.
 */
public class AppNotification {
    private long id;
    private String msg;
    private String title;
    private String ownedBy;
    private int notificationID;
    public AppNotification(String msg, String title, String ownedBy,int notificationID){
        this.msg = msg;
        this.title = title;
        this.ownedBy = ownedBy;
        this.notificationID =notificationID;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(String ownedBy) {
        this.ownedBy = ownedBy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
    }
}
