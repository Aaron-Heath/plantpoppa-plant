package com.plantpoppa.plant.resources;

import com.plantpoppa.plant.models.SimpleUser;
import com.plantpoppa.plant.models.UserPlant;
import com.plantpoppa.plant.models.Watering;
import com.plantpoppa.plant.models.dto.JournalRequestDto;
import com.plantpoppa.plant.services.JournalService;
import com.plantpoppa.plant.services.PlantService;
import com.plantpoppa.plant.services.UserPlantService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/api/journal")
public class JournalResource {
    private final PlantService plantService;
    private final JournalService journalService;
    private final UserPlantService userPlantService;

    @Autowired
    public JournalResource(PlantService plantService, JournalService journalService, UserPlantService userPlantService) {
        this.plantService = plantService;
        this.journalService = journalService;
        this.userPlantService = userPlantService;
    }

    @PostMapping
    ResponseEntity<?> createWatering(ServletRequest request,
                                     ServletResponse response,
                                     @RequestBody JournalRequestDto journalRequest) {
        SimpleUser simpleUser = (SimpleUser) request.getAttribute("userInfo");
        if(journalRequest.getEntityUuid().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing request parameters, please try again");
        }

        if(simpleUser == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong. Please try again.");
        }

        // fetching with uuid and userId to ensure user is the owner of the plant they are modifying
        Optional<UserPlant> optionalQueriedPlant = userPlantService.fetchUserPlantByUuidAndUserId(journalRequest.getEntityUuid(), simpleUser.getUserId());

        if(optionalQueriedPlant.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No userPlant found with given criteria.");
        }

        UserPlant userPlant = optionalQueriedPlant.get();

        Watering watering = journalService.createWatering(userPlant);

        HashMap<String,String> res = new HashMap<String,String>();
        res.put("message", "watering recorded");
        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    @DeleteMapping
    ResponseEntity<?> deleteWatering(ServletRequest request,
                                     ServletResponse response,
                                     @RequestBody JournalRequestDto journalRequest) {
        SimpleUser simpleUser = (SimpleUser) request.getAttribute("userInfo");

        if(simpleUser == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong. Please try again.");
        }

        journalService.deleteEntry(journalRequest.getEntityId());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
