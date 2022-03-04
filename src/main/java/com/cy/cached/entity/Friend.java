package com.cy.cached.entity;

/**
 * 好友表
 */
public class Friend {
    private String id;
    private String userId ;
    private String friendId;

    public Friend(String id, String userId, String friendId) {
        this.id = id;
        this.userId = userId;
        this.friendId = friendId;
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

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }
}
