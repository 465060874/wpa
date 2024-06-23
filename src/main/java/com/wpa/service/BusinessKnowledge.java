package com.wpa.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BusinessKnowledge {
    List<BusinessKnowledge> getBusinessKnowledgeListByType(String type);
}
