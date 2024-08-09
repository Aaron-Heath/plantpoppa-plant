package com.plantpoppa.plant.resources;

import com.plantpoppa.plant.models.SimpleUser;
import com.plantpoppa.plant.models.UserEntity;
import com.plantpoppa.plant.models.UserPlant;
import com.plantpoppa.plant.models.dto.UserPlantRequestDto;
import com.plantpoppa.plant.models.dto.UserPlantDto;
import com.plantpoppa.plant.security.CustomUserDetails;
import com.plantpoppa.plant.services.UserPlantService;
import com.plantpoppa.plant.services.UserService;
import jakarta.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-plant")
public class UserPlantResource {
    private final UserPlantService userPlantService;
    private final UserService userService;

    @Autowired
    UserPlantResource(UserPlantService userPlantService, UserService userService) {
        this.userPlantService = userPlantService;
        this.userService = userService;
    }

    @GetMapping
    ResponseEntity<?> fetchUserPlants(ServletRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {
        int userId = userDetails.getUserId();

        List<UserPlantDto> userPlants = userPlantService.fetchUserPlantDtosByUser(userId);

        return new ResponseEntity<>(userPlants, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> createUserPlant(ServletRequest request,
                                      @RequestBody UserPlantRequestDto userPlantRequestDto,
                                      @AuthenticationPrincipal CustomUserDetails userDetails) {
        UserEntity authenticatedUser = userService.findAuthenticatedUser(userDetails).orElseThrow(() -> new AuthenticationCredentialsNotFoundException("Authenteication credential not found"));


        Optional<UserPlant> createdPlant = userPlantService.createUserPlant(authenticatedUser,userPlantRequestDto);

        if (createdPlant.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(createdPlant.get(), HttpStatus.OK);
    }


    @GetMapping("/{userPlantUuid}")
    ResponseEntity<?> fetchUserPlantByUuid(@PathVariable String userPlantUuid) {
        Optional<UserPlant> optionalUserPlant = userPlantService.fetchUserPlantByUuid(userPlantUuid);
        if(optionalUserPlant.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "UserPlant not found");
        }
        UserPlant userPlant = optionalUserPlant.get();
        return new ResponseEntity<>(userPlant, HttpStatus.OK);
    }

    @PatchMapping("/{userPlantUuid}")
    ResponseEntity<?> updateUserPlantByUuid(ServletRequest request,
                                            @PathVariable String userPlantUuid,
                                            @RequestBody UserPlantRequestDto userPlantRequest) {
        SimpleUser simpleUser = (SimpleUser) request.getAttribute("userInfo");

        userPlantRequest.setUserPlantUuid(userPlantUuid);

        Optional<UserPlantDto> updatedUserPlantDto = userPlantService.updateUserPlant(userPlantRequest, simpleUser);

        if(updatedUserPlantDto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Unable to update at this time. Please try again later.");
        }

        return new ResponseEntity<>(updatedUserPlantDto.get(), HttpStatus.OK);

    }

    @DeleteMapping("/{userPlantUuid}")
    ResponseEntity<?> deleteUserPlantByUuid(ServletRequest request,
                                            @PathVariable String userPlantUuid) {
        SimpleUser simpleUser = (SimpleUser) request.getAttribute("userInfo");

        int result = userPlantService.deleteUserPlant(userPlantUuid, simpleUser);

        if (result == 99) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to delete record.");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
