package com.plantpoppa.plant.dao;

import com.plantpoppa.plant.models.Watering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

public interface WateringRepository extends JpaRepository<Watering, Integer> {

    Optional<Watering> getWateringById(int wateringId);

    @Modifying
    @Transactional
    @Query(value="INSERT INTO watering (user_plant_id, watering_date) " +
            "VALUES ((SELECT user_plant_id FROM user_plant WHERE uuid = ?1),?2)",
    nativeQuery = true)
    void insertWateringByUuid(String uuid, LocalDate wateringDate);

    @Transactional
    @Modifying
    void deleteById(int wateringId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE watering  SET watering_date = :wateringDate WHERE watering_id = :wateringId",
    nativeQuery = true)
    void updateWatering(@Param(value="wateringDate") LocalDate date, @Param(value = "wateringId") int wateringId);
}
