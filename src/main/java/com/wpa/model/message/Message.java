package com.wpa.model.message;

import java.util.List;

public class Message {
    private int id;
    private String categoryItemCode;
    private String description;
    private String queueTopic;
    private List<String> properties;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryItemCode() {
        return categoryItemCode;
    }

    public void setCategoryItemCode(String categoryItemCode) {
        this.categoryItemCode = categoryItemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQueueTopic() {
        return queueTopic;
    }

    public void setQueueTopic(String queueTopic) {
        this.queueTopic = queueTopic;
    }

    public List<String> getProperties() {
        return properties;
    }

    public void setProperties(List<String> properties) {
        this.properties = properties;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
