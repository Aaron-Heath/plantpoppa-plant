package com.plantpoppa.plant.models.dto;

public class CreateUserPlantRequestDto {
    private String nickname;
    private String plantUuid;

    public CreateUserPlantRequestDto(String nickname, String plantUuid){
        this.nickname = nickname;
        this.plantUuid = plantUuid;
    }

    public CreateUserPlantRequestDto() {

    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPlantUuid() {
        return plantUuid;
    }

    public void setPlantUuid(String plantUuid) {
        this.plantUuid = plantUuid;
    }
}
