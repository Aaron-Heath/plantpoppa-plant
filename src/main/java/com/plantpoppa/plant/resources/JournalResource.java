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
@RequestMapping("/api/user-plant/{userPlantUuid}/journal")
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

    @PostMapping("/water")
    ResponseEntity<?> createWatering(ServletRequest request,
                                     ServletResponse response,
                                     @PathVariable String userPlantUuid,
                                     @RequestBody JournalRequestDto journalRequest) {
        SimpleUser simpleUser = (SimpleUser) request.getAttribute("userInfo");

        if(simpleUser == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong. Please try again.");
        }

        // fetching with uuid and userId to ensure user is the owner of the plant they are modifying
        Optional<UserPlant> optionalQueriedPlant = userPlantService.fetchUserPlantByUuidAndUserId(userPlantUuid, simpleUser.getUserId());

        if(optionalQueriedPlant.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No userPlant found with given criteria.");
        }

        UserPlant userPlant = optionalQueriedPlant.get();

        if(journalRequest.getEntryDate() == null) {
            Watering watering = journalService.createWatering(userPlant);
        } else {
            Watering watering = journalService.createWatering(userPlant, journalRequest.getEntryDate());
        }
        HashMap<String,String> res = new HashMap<String,String>();
        res.put("message", "watering recorded");
        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    @PutMapping
    ResponseEntity<?> updateWatering(ServletRequest request,
                                     @RequestBody JournalRequestDto journalRequest) {
        SimpleUser simpleUser = (SimpleUser) request.getAttribute("userInfo");
        if(simpleUser == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong. Please try again.");
        }
        journalService.updateWatering(journalRequest.getEntryDate(), journalRequest.getEntityId());

        return new ResponseEntity<>(HttpStatus.OK);

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
