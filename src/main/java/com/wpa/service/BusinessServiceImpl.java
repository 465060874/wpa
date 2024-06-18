package com.wpa.service;

import com.wpa.model.business.Business;
import jakarta.enterprise.context.ApplicationScoped;
import org.springframework.stereotype.Service;

import java.util.List;

@ApplicationScoped
public class BusinessServiceImpl implements BusinessService{
    @Override
    public List<Business> getAllBusiness() {
        return List.of();
    }
}
