package com.wpa.model.business;

import javafx.beans.Observable;

public class Business {
    private String title;
    private String shortDescription;
    private String content;

    public Business() {

    }
    public Business(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
