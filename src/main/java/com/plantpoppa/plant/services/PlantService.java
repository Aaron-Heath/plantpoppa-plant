package com.plantpoppa.plant.services;

import com.plantpoppa.plant.dao.PlantRepository;
import com.plantpoppa.plant.dao.UserPlantRepository;
import com.plantpoppa.plant.models.Plant;
import com.plantpoppa.plant.models.UserPlant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

@Component
public class PlantService {

    private final PlantRepository plantRepository;
    private final UserPlantRepository userPlantRepository;

    @Autowired
    public PlantService(PlantRepository plantRepository, UserPlantRepository userPlantRepository) {
        this.userPlantRepository = userPlantRepository;
        this.plantRepository = plantRepository;
    }

    public List<Plant> fetchAll() {
        return plantRepository.fetchAll();
    }

    public Optional<Plant> fetchOneById(int plantId) {
        return plantRepository.fetchOneById(plantId);
    }


    public List<UserPlant> fetchAllUserPlants() {
        return userPlantRepository.fetchAllUserPlants();
    }

    public List<UserPlant> fetchUserPlantsByUser(int userId) {
        return userPlantRepository.fetchUserPlantByUser(userId);
    }

}
