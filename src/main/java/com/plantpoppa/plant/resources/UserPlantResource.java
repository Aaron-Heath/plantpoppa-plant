package com.plantpoppa.plant.resources;

import com.plantpoppa.plant.models.SimpleUser;
import com.plantpoppa.plant.models.UserPlant;
import com.plantpoppa.plant.models.dto.CreateUserPlantRequestDto;
import com.plantpoppa.plant.models.dto.UserPlantDto;
import com.plantpoppa.plant.services.UserPlantService;
import jakarta.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-plant")
public class UserPlantResource {
    private final UserPlantService userPlantService;

    @Autowired
    UserPlantResource(UserPlantService userPlantService) {
        this.userPlantService = userPlantService;
    }

    @GetMapping
    ResponseEntity<?> fetchUserPlants(ServletRequest request) {
        SimpleUser simpleUser = (SimpleUser) request.getAttribute("userInfo");
        int userId = simpleUser.getUserId();

        List<UserPlantDto> userPlants = userPlantService.fetchUserPlantDtosByUser(userId);

        return new ResponseEntity<>(userPlants, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> createUserPlant(ServletRequest request,
                                      @RequestBody CreateUserPlantRequestDto userPlantRequestDto) {
        SimpleUser simpleUser = (SimpleUser) request.getAttribute("userInfo");

        Optional<UserPlant> createdPlant = userPlantService.createUserPlant(simpleUser, userPlantRequestDto);

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

}
