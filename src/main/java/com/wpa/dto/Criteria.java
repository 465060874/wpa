package com.wpa.dto;

import de.saxsys.mvvmfx.Context;

public class Criteria implements Context {
    private String category;

    public Criteria(String category) {
        this.category = category;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
