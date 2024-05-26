package com.plantpoppa.plant.models;

public class SimpleUser {
    private String uuid;
    private int userId;

    public SimpleUser(String uuid, int userId) {
        this.uuid = uuid;
        this.userId = userId;
    }

    public SimpleUser() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
