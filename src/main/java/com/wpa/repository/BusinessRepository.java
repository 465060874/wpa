package com.wpa.repository;

import com.wpa.model.business.Business;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessRepository {
    List<Business> findAll();
}
