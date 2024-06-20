package com.wpa.test;

import org.springframework.stereotype.Service;

@Service
public class SharedDataService {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
