package com.wpa.service;

import com.wpa.entity.BusinessKnowledge;
import com.wpa.repository.BusinessKnowledgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BusinessKnowledgeService {
    @Autowired
    private BusinessKnowledgeRepository businessKnowledgeRepository;

    public List<BusinessKnowledge> findByType(String type) throws Exception {
        return businessKnowledgeRepository.findAllByType(type);
    }

    public BusinessKnowledge update(BusinessKnowledge businessKnowledge) {
        try {
            return businessKnowledgeRepository.save(businessKnowledge);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
