package com.example.nhat0.app3002.entity;

/**
 * Created by nhat0 on 13/3/2016.
 */
public class Category {
    private String title;
    private boolean selectForView;

    public Category(String title){
        this.title = title;
        selectForView = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelectForView() {
        return selectForView;
    }

    public void setSelectForView(boolean selectForView) {
        this.selectForView = selectForView;
    }
}
