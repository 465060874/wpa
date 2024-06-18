package com.wpa.repository;

import com.wpa.model.business.Business;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class BusinessRepositoryImpl implements BusinessRepository{
    @Override
    public List<Business> findAll() {
        return List.of();
    }
}
