package com.wpa.repository;

import com.wpa.model.business.Business;

import java.util.List;

public interface BusinessRepository {
    List<Business> findAll();
}
