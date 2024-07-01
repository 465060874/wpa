package com.wpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "business_knowledge")
public class BusinessKnowledge extends KnowledgeBase {
    @Lob
    @Column(name = "content", columnDefinition = "CLOB")
    private String content;

    public static BusinessKnowledge getInstance(String type){
        BusinessKnowledge businessKnowledge = new BusinessKnowledge();
        businessKnowledge.setType(type);
        businessKnowledge.setContent("");
        return businessKnowledge;
    }
    public BusinessKnowledge() {
    }

    public BusinessKnowledge(String title, String shortDescription, String content) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
