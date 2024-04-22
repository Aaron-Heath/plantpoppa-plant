package com.plantpoppa.plant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.plantpoppa.plant.models.UserPlant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPlantRepository extends JpaRepository<UserPlant, Integer> {
        @Query(value = "SELECT * FROM user_plant", nativeQuery = true)
        List<UserPlant> fetchAllUserPlants();

    @Query(value = "SELECT up.user_plant_id, up.plant_id, up.user_id, up.nickname, up.snooze, " +
            "p.common_name, p.scientific_name, p.water_frequency, p.water_info, p.sunlight, p.sunlight_info " +
        "FROM user_plant up " +
        "FULL OUTER JOIN plant p ON up.plant_id = p.plant_id " +
        "WHERE up.user_id = ?1",
    nativeQuery = true)
    List<UserPlant> fetchUserPlantByUser(int userId);

}
