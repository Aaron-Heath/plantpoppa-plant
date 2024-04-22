package com.plantpoppa.plant.services;

import com.plantpoppa.plant.dao.PlantRepository;
import com.plantpoppa.plant.models.Plant;
import com.plantpoppa.plant.models.UserPlant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PlantService {

    private final PlantRepository plantRepository;

    @Autowired
    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public List<Plant> fetchAll() {
        return plantRepository.fetchAll();
    }

    public Optional<Plant> fetchOneById(int plantId) {
        return plantRepository.fetchOneById(plantId);
    }

    public List<UserPlant> fetchUserPlantsByUser(int userId) {
        return plantRepository.fetchUserPlantByUser(userId);
    }

    public List<UserPlant> fetchPlantsByUser(int userId) {
        return plantRepository.fetchPlantsByUser(userId);
    }
}
