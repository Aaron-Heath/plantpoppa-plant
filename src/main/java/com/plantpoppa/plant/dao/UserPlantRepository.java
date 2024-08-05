package com.plantpoppa.plant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.plantpoppa.plant.models.UserPlant;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPlantRepository extends JpaRepository<UserPlant, Integer> {

    List<UserPlant> findAll();

    List<UserPlant> findByUserId(int uuid);

    Optional<UserPlant> findByUuid(String uuid);

    Optional<UserPlant> findByUuidAndUserId(String uuid, int userId);

}
