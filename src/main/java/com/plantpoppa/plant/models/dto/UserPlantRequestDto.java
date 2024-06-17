package com.plantpoppa.plant.models.dto;

public class UserPlantRequestDto {
    private String nickname;
    private String userPlantUuid;
    private String plantUuid;

    public UserPlantRequestDto(String nickname, String userPlantUuid, String plantUuid){
        this.nickname = nickname;
        this.userPlantUuid = userPlantUuid;
        this.plantUuid = plantUuid;
    }

    public UserPlantRequestDto() {

    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserPlantUuid() {
        return userPlantUuid;
    }

    public void setUserPlantUuid(String userPlantUuid) {
        this.userPlantUuid = userPlantUuid;
    }

    public String getPlantUuid() {
        return plantUuid;
    }

    public void setPlantUuid(String plantUuid) {
        this.plantUuid = plantUuid;
    }
}
