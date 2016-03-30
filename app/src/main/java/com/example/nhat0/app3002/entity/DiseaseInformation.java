package com.example.nhat0.app3002.entity;



/**
 * Created by nhat0 on 13/3/2016.
 */
public class DiseaseInformation extends HealthInformation {
    private String diseaseType;
    private AppNotification notification;
    public DiseaseInformation(String title, String content, int urgentPriority, String diseaseType) {
        super( title, content, urgentPriority);
        this.diseaseType = diseaseType;
        notification = null;

    }

    public DiseaseInformation() {

    }


    public String getDiseaseType() {
        return diseaseType;
    }

    public void setDiseaseType(String diseaseType) {
        this.diseaseType = diseaseType;
    }
}
