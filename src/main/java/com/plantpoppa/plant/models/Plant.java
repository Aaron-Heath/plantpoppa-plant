package com.plantpoppa.plant.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "plant")
public class Plant {
    @JsonProperty("plant_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int plantId;

    @Column(name="uuid")
    private String uuid;

    @JsonProperty("common_name")
    @Column(name="common_name")
    private String commonName;

    @Column(name="scientific_name")
    @JsonProperty("scientific_name")
    private String scientificName;

    @Column(name="water_frequency")
    @JsonProperty("water_frequency")
    private int waterFrequency;

    @Column(name="water_info")
    @JsonProperty("water_info")
    private String waterInfo;

    @Column(name="sunlight")
    private String sunlight;

    @Column(name="sunlight_info")
    @JsonProperty("sunlight_info")
    private String sunlightInfo;

    @OneToMany(mappedBy = "plant") // attribute name on the other side of relationship
    private List<UserPlant> userPlants;

    public Plant(int plantId, String uuid, String commonName, String scientificName, int waterFrequency, String waterInfo, String sunlight, String sunlightInfo) {
        this.plantId = plantId;
        this.uuid = uuid;
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.waterFrequency = waterFrequency;
        this.waterInfo = waterInfo;
        this.sunlight = sunlight;
        this.sunlightInfo = sunlightInfo;
    }

    public Plant(String uuid, String commonName, String scientificName, int waterFrequency, String waterInfo, String sunlight, String sunlightInfo) {
        this.uuid = uuid;
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.waterFrequency = waterFrequency;
        this.waterInfo = waterInfo;
        this.sunlight = sunlight;
        this.sunlightInfo = sunlightInfo;
    }

    public Plant() {
    }

    public void removeUserPlant(UserPlant userPlant) {
        this.userPlants.remove(userPlant);
    }

    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
