package com.cy.cached.entity;

/**
 * 朋友圈信息表
 */
public class Mood {
    private String id;
    private String userId;
    private String content;
    private long dateTime;

    public Mood() {

    }

    public Mood(String id, String userId, String content, long dateTime) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.dateTime = dateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }
}
