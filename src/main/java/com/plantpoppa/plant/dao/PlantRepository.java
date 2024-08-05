package com.plantpoppa.plant.dao;

import com.plantpoppa.plant.models.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Integer> {

    List<Plant> findAll();

    Optional<Plant> findOneByPlantId(int plantId);

    Optional<Plant> findByUuid(String uuid);
}
