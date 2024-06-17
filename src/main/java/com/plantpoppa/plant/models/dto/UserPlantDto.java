package com.plantpoppa.plant.models.dto;

import com.plantpoppa.plant.models.Plant;

import java.time.LocalDate;

public class UserPlantDto {
    private String uuid;
    private String nickname;
    private LocalDate snooze;
    private Plant plant;
    private LocalDate lastWatered;

    public UserPlantDto(String uuid, String nickname, LocalDate snooze, Plant plant, LocalDate lastWatered) {
        this.uuid = uuid;
        this.nickname = nickname;
        this.snooze = snooze;
        this.plant = plant;
        this.lastWatered = lastWatered;
    }

    public UserPlantDto() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDate getSnooze() {
        return snooze;
    }

    public void setSnooze(LocalDate snooze) {
        this.snooze = snooze;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public LocalDate getLastWatered() {
        return lastWatered;
    }

    public void setLastWatered(LocalDate lastWatered) {
        this.lastWatered = lastWatered;
    }

    public static class UserPlantDtoBuilder {
        private String uuid;
        private String nickname;
        private LocalDate snooze;
        private Plant plant;
        private LocalDate lastWatered;

        public UserPlantDtoBuilder() {

        }

        public UserPlantDtoBuilder uuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public UserPlantDtoBuilder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public UserPlantDtoBuilder snooze(LocalDate snooze) {
            this.snooze = snooze;
            return this;
        }

        public UserPlantDtoBuilder plant(Plant plant) {
            this.plant = plant;
            return this;
        }

        public UserPlantDtoBuilder lastWatered(LocalDate lastWatered) {
            this.lastWatered = lastWatered;
            return this;
        }

        public UserPlantDto build() {
            return new UserPlantDto(
                    this.uuid,
                    this.nickname,
                    this.snooze,
                    this.plant,
                    this.lastWatered
            );
        }
    }
}
