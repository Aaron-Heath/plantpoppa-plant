package com.plantpoppa.plant.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="user_plant")
public class UserPlant {
//    @JsonProperty("user_plant_id")
    @Column(name = "user_plant_id")
    private @Id int userPlantId;

    @Column(name="nickname")
    private String nickname;

//    @JsonProperty("plant_id")
    @Column(name="plant_id")
    private int plantId;

//    @JsonProperty("user_id")
    @Column(name="user_id")
    private int userId;

    @Column(name="snooze")
    private LocalDate snooze;

    public UserPlant(int userPlantId, String nickname, int plantId, int user_id, LocalDate snooze) {
        this.userPlantId = userPlantId;
        this.nickname = nickname;
        this.plantId = plantId;
        this.userId = user_id;
        this.snooze = snooze;
    }

    public UserPlant() {
    }

    public int getUserPlantId() {
        return userPlantId;
    }

    public void setUserPlantId(int userPlantId) {
        this.userPlantId = userPlantId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getSnooze() {
        return snooze;
    }

    public void setSnooze(LocalDate snooze) {
        this.snooze = snooze;
    }
}
