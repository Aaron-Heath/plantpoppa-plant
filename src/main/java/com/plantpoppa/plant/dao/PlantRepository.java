package com.plantpoppa.plant.dao;

import com.plantpoppa.plant.models.Plant;
import com.plantpoppa.plant.models.UserPlant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public interface PlantRepository extends JpaRepository<Plant, Integer> {

    @Query(value="SELECT * FROM plant",
    nativeQuery = true)
    List<Plant> fetchAll();

    @Query(value="SELECT * FROM plant WHERE " +
            "plant_id = ?1",
    nativeQuery = true)
    Optional<Plant> fetchOneById(int plantId);

    Optional<Plant> findByUuid(String uuid);
}
