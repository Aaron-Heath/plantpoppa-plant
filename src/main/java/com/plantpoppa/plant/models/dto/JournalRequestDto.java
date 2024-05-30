package com.plantpoppa.plant.models.dto;

public class JournalRequestDto {
    private String userPlantUuid;

    public JournalRequestDto(String userPlantUuid) {
        this.userPlantUuid = userPlantUuid;
    }

    public JournalRequestDto() {
    }

    public String getUserPlantUuid() {
        return userPlantUuid;
    }

    public void setUserPlantUuid(String userPlantUuid) {
        this.userPlantUuid = userPlantUuid;
    }
}
