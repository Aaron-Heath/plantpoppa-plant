package com.plantpoppa.plant.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
//@Table(name="watering")
public class Watering {
//    @JsonProperty("watering_id")
    @Column(name = "watering_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id int wateringId;

//    @Column(name = "user_plant_id")
////    @JsonProperty("user_plant_id")
//    private int userPlantId;

    @Column(name = "watering_date")
//    @JsonProperty("watering_date")
    LocalDate wateringDate;

    @ManyToOne
    @JoinColumn(name = "user_plant_id", nullable = false)
    private UserPlant userPlant;

    public Watering(int wateringId, LocalDate wateringDate) {
        this.wateringId = wateringId;
        this.userPlant = userPlant;
        this.wateringDate = wateringDate;
    }

    public Watering() {
    }

    public int getWateringId() {
        return wateringId;
    }

    public void setWateringId(int wateringId) {
        this.wateringId = wateringId;
    }

    public LocalDate getWateringDate() {
        return wateringDate;
    }

    public void setWateringDate(LocalDate wateringDate) {
        this.wateringDate = wateringDate;
    }

    public UserPlant getUserPlant() {
        return userPlant;
    }

    public void setUserPlant(UserPlant userPlant) {
        this.userPlant = userPlant;
    }
}
