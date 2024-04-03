package com.plantpoppa.plant.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Watering {
    @JsonProperty("watering_id")
    private int wateringId;

    @JsonProperty("user_plant_id")
    private int userPlantId;

    @JsonProperty("watering_date")
    LocalDate wateringDate;

    public Watering(int wateringId, int userPlantId, LocalDate wateringDate) {
        this.wateringId = wateringId;
        this.userPlantId = userPlantId;
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

    public int getUserPlantId() {
        return userPlantId;
    }

    public void setUserPlantId(int userPlantId) {
        this.userPlantId = userPlantId;
    }

    public LocalDate getWateringDate() {
        return wateringDate;
    }

    public void setWateringDate(LocalDate wateringDate) {
        this.wateringDate = wateringDate;
    }
}
