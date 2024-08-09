package com.plantpoppa.plant.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="watering")
public class Watering {
    @Column(name = "watering_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id int id;

    @Column(name = "watering_date")
    LocalDate wateringDate;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_plant_id", nullable = false)
    private UserPlant userPlant;

    public Watering(int id, LocalDate wateringDate) {
        this.id = id;
        this.userPlant = userPlant;
        this.wateringDate = wateringDate;
    }

    public Watering() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
