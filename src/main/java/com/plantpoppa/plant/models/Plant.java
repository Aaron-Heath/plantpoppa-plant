package com.plantpoppa.plant.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Plant {
    @JsonProperty("plant_id")
    private @Id int plantId;

    @JsonProperty("common_name")
    private String commonName;

    @JsonProperty("scientific_name")
    private String scientificName;

    @JsonProperty("water_frequency")
    private int waterFrequency;

    @JsonProperty("water_info")
    private String waterInfo;

    private String sunlight;

    @JsonProperty("sunlight_info")
    private String sunlightInfo;

    public Plant(int plantId, String commonName, String scientificName, int waterFrequency, String waterInfo, String sunlight, String sunlightInfo) {
        this.plantId = plantId;
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.waterFrequency = waterFrequency;
        this.waterInfo = waterInfo;
        this.sunlight = sunlight;
        this.sunlightInfo = sunlightInfo;
    }

    public Plant(String commonName, String scientificName, int waterFrequency, String waterInfo, String sunlight, String sunlightInfo) {
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.waterFrequency = waterFrequency;
        this.waterInfo = waterInfo;
        this.sunlight = sunlight;
        this.sunlightInfo = sunlightInfo;
    }

    public Plant() {

    }

    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public int getWaterFrequency() {
        return waterFrequency;
    }

    public void setWaterFrequency(int waterFrequency) {
        this.waterFrequency = waterFrequency;
    }

    public String getWaterInfo() {
        return waterInfo;
    }

    public void setWaterInfo(String waterInfo) {
        this.waterInfo = waterInfo;
    }

    public String getSunlight() {
        return sunlight;
    }

    public void setSunlight(String sunlight) {
        this.sunlight = sunlight;
    }

    public String getSunlightInfo() {
        return sunlightInfo;
    }

    public void setSunlightInfo(String sunlightInfo) {
        this.sunlightInfo = sunlightInfo;
    }
}
