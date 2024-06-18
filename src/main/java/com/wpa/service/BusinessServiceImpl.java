package com.wpa.service;

import com.wpa.model.business.Business;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService{
    @Override
    public List<Business> getAllBusiness() {
        return List.of();
    }
}
