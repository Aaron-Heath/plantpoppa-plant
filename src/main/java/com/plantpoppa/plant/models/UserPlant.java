package com.plantpoppa.plant.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="user_plant")
public class UserPlant {

    @Column(name = "user_plant_id")
    private @Id int userPlantId;

    @Column(name="uuid")
    private String uuid = String.valueOf(UUID.randomUUID());

    @Column(name="nickname")
    private String nickname;


    @Column(name="user_id")
    private int userId;

    @Column(name="snooze")
    private LocalDate snooze;

    @ManyToOne
    @JoinColumn(name="plant_id")
    private Plant plant;

    public UserPlant(int userPlantId, String uuid, String nickname,  int user_id, LocalDate snooze, Plant plant) {
        this.userPlantId = userPlantId;
        if(uuid == null) {
            this.uuid = String.valueOf(UUID.randomUUID());
        } else {
            this.uuid = uuid;
        }
        this.nickname = nickname;
        this.userId = user_id;
        this.snooze = snooze;
        this.plant = plant;
    }

    public UserPlant(int userPlantId, String uuid, String nickname,  int user_id, LocalDate snooze) {
        this.userPlantId = userPlantId;
        this.uuid = uuid;
        this.nickname = nickname;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }
}
