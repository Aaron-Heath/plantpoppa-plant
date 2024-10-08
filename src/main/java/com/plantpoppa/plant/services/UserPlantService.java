package com.plantpoppa.plant.services;

import com.plantpoppa.plant.dao.UserPlantRepository;
import com.plantpoppa.plant.models.*;
import com.plantpoppa.plant.models.dto.UserPlantRequestDto;
import com.plantpoppa.plant.models.dto.UserPlantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserPlantService {
    private final UserPlantRepository userPlantRepository;
    private final PlantService plantService;

    @Autowired
    public UserPlantService(UserPlantRepository userPlantRepository, PlantService plantService) {
        this.userPlantRepository = userPlantRepository;
        this.plantService = plantService;
    }

    public long count() {
        return userPlantRepository.count();
    }

    public Optional<UserPlant> createUserPlant(UserEntity user, UserPlantRequestDto userPlantRequest) {

        // Get plant from plant service
        String plantUuid = userPlantRequest.getPlantUuid();
        Optional<Plant> queriedPlant = plantService.fetchOneByUuid(plantUuid);
        if(queriedPlant.isEmpty()) {
            System.out.println("No plant found with given id");
            return Optional.empty();
        }

        Plant foundPlant = queriedPlant.get();

        // Create UserPlant from dto
        UserPlant userPlant = new UserPlant();
        userPlant.setNickname(userPlantRequest.getNickname());
        userPlant.setUser(user);
        userPlant.setPlant(foundPlant);

        //save/flush changes
        return Optional.of(userPlantRepository.saveAndFlush(userPlant));
    }

    public Optional<UserPlant> fetchUserPlantByUuid(String uuid) {
        return userPlantRepository.findByUuid(uuid);
    }

    public List<UserPlant> fetchAllUserPlants() {
        return userPlantRepository.findAll();
    }

    public List<UserPlant> fetchUserPlantsByUser(int userId) {
        return userPlantRepository.findByUserId(userId);
    }

    public List<UserPlantDto> fetchUserPlantDtosByUser(int userId) {
        List<UserPlant> userPlants = userPlantRepository.findByUserId(userId);
        List<UserPlantDto> userPlantDtos = new ArrayList<>();
        userPlants.forEach(userPlant -> {
            userPlantDtos.add(
                    new UserPlantDto.UserPlantDtoBuilder()
                            .uuid(userPlant.getUuid())
                            .nickname(userPlant.getNickname())
                            .snooze(userPlant.getSnooze())
                            .plant(userPlant.getPlant())
                            .lastWatered(userPlant.getLastWatered())
                            .nextWatering(userPlant.getNextWatering())
                            .waterings(userPlant.getWaterings())
                            .build()
            );
        });

        return userPlantDtos;
    }

    public Optional<UserPlant> findUserPlantByUuidAndUserId(String uuid, int userId) {
        return userPlantRepository.findByUuidAndUserId(uuid, userId);
    }

    public Optional<UserPlantDto> updateUserPlant(UserPlantRequestDto userPlantRequest,
                                                  int userId) {
        Optional<UserPlant> queriedUserPlant = userPlantRepository.findByUuidAndUserId(userPlantRequest.getUserPlantUuid(), userId);

        if(queriedUserPlant.isEmpty()) {
            System.out.println("No userPlant found");
            return Optional.empty();
        }

        UserPlant foundUserPlant = queriedUserPlant.get();

        // Update userPlant
        if(userPlantRequest.getNickname() != null && !userPlantRequest.getNickname().isBlank()) {
            foundUserPlant.setNickname(userPlantRequest.getNickname());
        }

        if(userPlantRequest.getPlantUuid() != null && !userPlantRequest.getPlantUuid().isBlank()) {
            Optional<Plant> queriedPlant = plantService.fetchOneByUuid(userPlantRequest.getPlantUuid());
            if(queriedPlant.isEmpty()) {
                System.out.println("No plant found");
                return Optional.empty();
            }

            Plant foundPlant = queriedPlant.get();

            foundUserPlant.setPlant(foundPlant);
        }


        return Optional.of(userPlantRepository.saveAndFlush(foundUserPlant).toDto());

    }

    public int deleteUserPlant(String userPlantUuid, int userId) {
        Optional<UserPlant> queriedUserPlant = userPlantRepository.findByUuidAndUserId(userPlantUuid, userId);

        if(queriedUserPlant.isEmpty()) {
            return 99; // placeholder int to denote no record found
        }

        UserPlant foundUserPlant = queriedUserPlant.get();

        for (Watering watering : foundUserPlant.getWaterings()) { // Remove all child entries. Required for JPA relationship
            foundUserPlant.removeWatering(watering);
        }
        foundUserPlant.getWaterings().clear();
//        foundUserPlant.removePlant();


        userPlantRepository.delete(foundUserPlant);
        return 0; // placeholder number to indicate all went well
    }

    public Optional<UserPlant> refreshUserPlant(UserPlant userPlant) {
        return userPlantRepository.findByUuid(userPlant.getUuid());
    }
}
