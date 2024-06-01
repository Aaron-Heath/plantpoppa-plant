package com.plantpoppa.plant.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Watering {
    @Column(name = "watering_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id int wateringId;

    @Column(name = "watering_date")
    LocalDate wateringDate;

    @ManyToOne
    @JsonBackReference
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
