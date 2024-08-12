package com.plantpoppa.plant.services;

import com.plantpoppa.plant.dao.PlantRepository;
import com.plantpoppa.plant.dao.UserPlantRepository;
import com.plantpoppa.plant.models.Plant;
import com.plantpoppa.plant.models.UserPlant;
import com.plantpoppa.plant.models.Watering;
import com.plantpoppa.plant.models.dto.UserPlantDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PlantService {

    private final PlantRepository plantRepository;

    @Autowired
    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public long count() {
        return plantRepository.count();
    }

    public List<Plant> fetchAll() {
        return plantRepository.findAll();
    }

    public Optional<Plant> fetchOneById(int plantId) {
        return plantRepository.findOneByPlantId(plantId);
    }

    public Optional<Plant> fetchOneByUuid(String uuid) {return plantRepository.findByUuid(uuid);}
}
