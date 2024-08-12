package com.plantpoppa.plant.models.dto;


import lombok.Data;

@Data
public class AuthResponseDto {
    String token;
    String type;

    public AuthResponseDto(String accessToken) {
        this.token = accessToken;
        this.type = "Bearer ";
    }
}
