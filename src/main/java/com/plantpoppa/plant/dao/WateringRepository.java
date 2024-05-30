package com.plantpoppa.plant.dao;

import com.plantpoppa.plant.models.Watering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface WateringRepository extends JpaRepository<Watering, Integer> {

    @Modifying
    @Transactional
    @Query(value="INSERT INTO watering (user_plant_id, watering_date) " +
            "VALUES ((SELECT user_plant_id FROM user_plant WHERE uuid = ?1),?2)",
    nativeQuery = true)
    void insertWateringByUuid(String uuid, LocalDate wateringDate);

}
