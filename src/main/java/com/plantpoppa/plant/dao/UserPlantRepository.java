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

//    @Query(value = "SELECT * " +
//        "FROM user_plant " +
//        "FULL OUTER JOIN plant ON user_plant.plant_id = plant.plant_id " +
//        "WHERE user_plant.user_id = ?1",
//    nativeQuery = true)
//    List<UserPlant> fetchUserPlantByUser(int userId);

}
