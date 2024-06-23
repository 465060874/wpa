package com.wpa.service;
import com.wpa.model.business.Business;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BusinessService {
    List<Business> getAllBusiness();

}
