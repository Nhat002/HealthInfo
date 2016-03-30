package com.example.nhat0.app3002.entity;


/**
 * Created by nhat0 on 13/3/2016.
 */
public class HealthcareInformation extends HealthInformation {
    private String category;
    private AppNotification notification;
    public HealthcareInformation(String title, String content, int urgentPriority, String category) {
        super( title, content, urgentPriority);
        this.category = category;
        notification = null;
    }

    public HealthcareInformation() {
        super();
    }

    public String getCategory() {
        return category;
    }

    public AppNotification getNotification() {
        return notification;
    }

    public void setNotification(AppNotification notification) {
        this.notification = notification;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
