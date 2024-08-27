package com.plantpoppa.plant.models.dto;

import com.plantpoppa.plant.models.Plant;
import com.plantpoppa.plant.models.Watering;
import lombok.Data;

import java.time.LocalDate;
import java.util.Collection;

@Data
public class UserPlantDto {
    private String uuid;
    private String nickname;
    private LocalDate snooze;
    private Plant plant;
    private LocalDate lastWatered;
    private LocalDate nextWatering;
    private Collection<Watering> waterings;

    public UserPlantDto(String uuid, String nickname, LocalDate snooze, Plant plant, LocalDate lastWatered, LocalDate nextWatering, Collection<Watering> waterings) {
        this.uuid = uuid;
        this.nickname = nickname;
        this.snooze = snooze;
        this.plant = plant;
        this.lastWatered = lastWatered;
        this.nextWatering = nextWatering;
        this.waterings = waterings;
    }

    public UserPlantDto() {
    }

    public static class UserPlantDtoBuilder {
        private String uuid;
        private String nickname;
        private LocalDate snooze;
        private Plant plant;
        private LocalDate lastWatered;
        private LocalDate nextWatering;
        private Collection<Watering> waterings;

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

        public UserPlantDtoBuilder nextWatering (LocalDate nextWatering) {
            this.nextWatering = nextWatering;
            return this;
        }

        public UserPlantDtoBuilder waterings(Collection<Watering> waterings) {
            this.waterings = waterings;
            return this;
        }

        public UserPlantDto build() {
            return new UserPlantDto(
                    this.uuid,
                    this.nickname,
                    this.snooze,
                    this.plant,
                    this.lastWatered,
                    this.nextWatering,
                    this.waterings
            );
        }
    }
}
