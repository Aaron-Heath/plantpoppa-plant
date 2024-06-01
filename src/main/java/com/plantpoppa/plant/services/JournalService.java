package com.plantpoppa.plant.services;

import com.plantpoppa.plant.dao.WateringRepository;
import com.plantpoppa.plant.models.UserPlant;
import com.plantpoppa.plant.models.Watering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class JournalService {
    private final WateringRepository wateringRepository;
    private final PlantService plantService;

    @Autowired
    public JournalService(WateringRepository wateringRepository, PlantService plantService) {
        this.wateringRepository = wateringRepository;
        this.plantService = plantService;
    }

    /**
     * Creates a watering using today's date. Corresponds with the "quick water" action in the ui.
     * @param userPlant to maintain relationship between data.
     * @return created watering
     * */
    public Watering createWatering(UserPlant userPlant) {
        LocalDate today = LocalDate.now();
        Watering watering = new Watering();
        watering.setUserPlant(userPlant);
        watering.setWateringDate(today);
        return wateringRepository.save(watering);
    }

    /**
     * Creates a watering using a user-input date. Allows backdating.
     * @param userPlant
     * @param wateringDate
     * @return created watering.
     */
    public Watering createWatering(UserPlant userPlant, LocalDate wateringDate) {
        LocalDate today = LocalDate.now();
        if (wateringDate.isAfter(today)) {
            return null;
        }

        Watering watering = new Watering();
        watering.setUserPlant(userPlant);
        watering.setWateringDate(wateringDate);
        return wateringRepository.save(watering);
    }

    public void updateWatering(LocalDate date, int wateringId) {
        wateringRepository.updateWatering(date, wateringId);
    }

    public void deleteEntry(int wateringId) {
        wateringRepository.deleteByWateringId(wateringId);
        System.out.println("Journal deleted");
    }
}
