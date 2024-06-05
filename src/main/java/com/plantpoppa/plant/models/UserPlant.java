package com.plantpoppa.plant.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.plantpoppa.plant.models.dto.UserPlantDto;
import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

import java.time.LocalDate;
import java.util.Set;
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

    @JsonManagedReference
    @OneToMany(mappedBy = "userPlant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Watering> waterings;

    @Formula("(SELECT MAX(w.watering_date) FROM watering w WHERE w.user_plant_id = user_plant_id)")
    private LocalDate lastWatered;

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

    public Set<Watering> getWaterings() {
        return waterings;
    }

    public void setWaterings(Set<Watering> waterings) {
        this.waterings = waterings;
    }

    public LocalDate getLastWatered() {
        return lastWatered;
    }

    public void setLastWatered(LocalDate lastWatered) {
        this.lastWatered = lastWatered;
    }

    public UserPlantDto toDto() {
        return new UserPlantDto.UserPlantDtoBuilder()
                .uuid(this.uuid)
                .nickname(this.nickname)
                .plant(this.plant)
                .snooze(this.snooze)
                .lastWatered(this.lastWatered)
                .build();
    }
}
