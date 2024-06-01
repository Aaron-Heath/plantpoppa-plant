package com.plantpoppa.plant.resources;


import com.plantpoppa.plant.models.Plant;
import com.plantpoppa.plant.models.SimpleUser;
import com.plantpoppa.plant.models.UserPlant;
import com.plantpoppa.plant.models.dto.UserPlantDto;
import com.plantpoppa.plant.services.PlantService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/plant")
public class PlantResource {
    private final PlantService plantService;

    @Autowired
    PlantResource(PlantService plantService){this.plantService = plantService;}

    @GetMapping("/")
    List<Plant> fetchAll() {
        return plantService.fetchAll();
    }

    @GetMapping("/{plantId}")
    ResponseEntity<?> fetchOneById(@PathVariable int plantId) {
        Optional<Plant> plant = plantService.fetchOneById(plantId);
        return new ResponseEntity<>(plant.orElse(null), plant.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }
}
