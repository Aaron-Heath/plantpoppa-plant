package com.plantpoppa.plant.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;


public class UserPlantTest {
    private UserEntity user = new UserEntity.UserBuilder()
            .email("test.user@email.com")
            .firstname("test")
            .lastname("user")
            .phone("2155555555")
            .zip("12345")
            .pw_hash("randomString")
            .user_id(1)
            .uuid("randomString2")
            .build();



    private Plant plant = new Plant(
           "RandomString",
           "Aloe Vera",
           "Aloe Vera",
            7,
            "Water as needed",
            "Indirect",
            " Bright, indirect light, but can tolerate direct sunlight"
            );

    private UserPlant userPlant;

    @BeforeEach
    public void setup() {
        userPlant = new UserPlant();
        userPlant.setPlant(plant);
        userPlant.setWaterings(null);
        userPlant.setUuid("randomString");
        userPlant.setNickname("Aloe Vera");
        userPlant.setLastWatered(null);
        userPlant.setId(1);
    }

    @Test
    public void givenPlantNoWatering_NextWateringIsToday() {
        LocalDate actual = userPlant.getNextWatering();
        LocalDate expected = LocalDate.now();

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void givenPlantWithWatering_NextWateringIsFrequencyAddedToLast() {
        // Set date to August 1, 2024
        LocalDate lastWatered = LocalDate.of(2024,Month.AUGUST,1);
        LocalDate expected = LocalDate.of(2024, Month.AUGUST,8);
        userPlant.setLastWatered(lastWatered);
        LocalDate actual = userPlant.getNextWatering();

        Assertions.assertEquals(expected, actual);
    }

}
