package com.plantpoppa.plant.resources;


import com.plantpoppa.plant.models.UserPlant;
import com.plantpoppa.plant.models.dto.JournalRequestDto;
import com.plantpoppa.plant.security.CustomUserDetails;
import com.plantpoppa.plant.services.JournalService;
import com.plantpoppa.plant.services.UserPlantService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/user-plant/{userPlantUuid}/journal")
public class JournalResource {
    private final JournalService journalService;
    private final UserPlantService userPlantService;

    @Autowired
    public JournalResource(JournalService journalService, UserPlantService userPlantService) {
        this.journalService = journalService;
        this.userPlantService = userPlantService;
    }

    @PostMapping("/water")
    ResponseEntity<?> createWatering(ServletRequest request,
                                     ServletResponse response,
                                     @PathVariable String userPlantUuid,
                                     @RequestBody JournalRequestDto journalRequest,
                                     @AuthenticationPrincipal CustomUserDetails userDetails) {


        // fetching with uuid and userId to ensure user is the owner of the plant they are modifying
        Optional<UserPlant> optionalQueriedPlant = userPlantService.fetchUserPlantByUuidAndUserId(userPlantUuid, userDetails.getUserId());

        if(optionalQueriedPlant.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No userPlant found with given criteria.");
        }

        UserPlant userPlant = optionalQueriedPlant.get();

        if(journalRequest.getEntryDate() == null) {
            userPlant = journalService.createWatering(userPlant);
        } else {
            userPlant = journalService.createWatering(userPlant, journalRequest.getEntryDate());
        }

        Optional<UserPlant> updatedUserPlant = userPlantService.refreshUserPlant(userPlant);
        return new ResponseEntity<>(updatedUserPlant.get(), HttpStatus.OK);

    }

    @PutMapping("/water")
    ResponseEntity<?> updateWatering(ServletRequest request,
                                     @RequestBody JournalRequestDto journalRequest) {

        journalService.updateWatering(journalRequest.getEntryDate(), journalRequest.getEntityId());

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/water")
    ResponseEntity<?> deleteWatering(ServletRequest request,
                                     ServletResponse response,
                                     @RequestBody JournalRequestDto journalRequest) {
        journalService.deleteEntry(journalRequest.getEntityId());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
