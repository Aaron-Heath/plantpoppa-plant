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

    public Watering createWatering(UserPlant userPlant) {

        LocalDate today = LocalDate.now();
        Watering watering = new Watering();
        watering.setUserPlant(userPlant);
        watering.setWateringDate(today);
        return wateringRepository.save(watering);

    }
}
