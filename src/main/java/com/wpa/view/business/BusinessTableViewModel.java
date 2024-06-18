package com.wpa.view.business;

import com.wpa.model.business.Business;

public class BusinessTableViewModel {
    private Business business;

    public BusinessTableViewModel() {

    }
    public BusinessTableViewModel(Business business) {
        this.business = business;
    }

    public String getTitle() {
        return business.getTitle();
    }

    public String getContent() {
        return business.getContent();
    }


    // getters and setters

    public Business getBusiness() {
        return business;
    }
}
