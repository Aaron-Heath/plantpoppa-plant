package com.plantpoppa.plant.services;

import com.plantpoppa.plant.dao.WateringRepository;
import com.plantpoppa.plant.models.UserEntity;
import com.plantpoppa.plant.models.UserPlant;
import com.plantpoppa.plant.models.Watering;
import com.plantpoppa.plant.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Component
public class JournalService {
    private final WateringRepository wateringRepository;
    private final PlantService plantService;

    @Autowired
    public JournalService(WateringRepository wateringRepository, PlantService plantService) {
        this.wateringRepository = wateringRepository;
        this.plantService = plantService;
    }

    private boolean isOwner(Watering watering, int userId) {
        return watering.getUserPlant().getUser().getId() == userId;
    }

    private boolean isOwner(UserPlant userPlant, int userId) {
        return userPlant.getUser().getId() == userId;
    }

    /**
     * Creates a watering using today's date. Corresponds with the "quick water" action in the ui.
     * @param userPlant to maintain relationship between data.
     * @return created watering
     * */
    public UserPlant createWatering(UserPlant userPlant, int userId) {
        if(!isOwner(userPlant, userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        LocalDate today = LocalDate.now();
        Watering watering = new Watering();
        watering.setUserPlant(userPlant);
        watering.setWateringDate(today);
        wateringRepository.save(watering);

        return userPlant;
    }

    /**
     * Creates a watering using a user-input date. Allows backdating.
     * @param userPlant
     * @param wateringDate
     * @return created watering.
     */
    public UserPlant createWatering(UserPlant userPlant, LocalDate wateringDate, int userId) {
        if(!isOwner(userPlant, userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        LocalDate today = LocalDate.now();
        if (wateringDate.isAfter(today)) {
            return null;
        }

        Watering watering = new Watering();
        watering.setUserPlant(userPlant);
        watering.setWateringDate(wateringDate);
        wateringRepository.save(watering);

        return userPlant;
    }

    public void updateWatering(LocalDate date, int wateringId, CustomUserDetails authenticatedUser) {
        Watering watering = wateringRepository.findById(wateringId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Watering not found."));
        if(!isOwner(watering, authenticatedUser.getUserId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not have access to this resource.");
        }
        wateringRepository.updateWatering(date, wateringId);
    }

    public void deleteEntry(int wateringId, int userId) {
        Watering watering = wateringRepository.getWateringById(wateringId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if(!isOwner(watering, userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not have access to this resource.");
        }
        wateringRepository.deleteById(wateringId);
        System.out.println("Journal deleted");
    }
}
