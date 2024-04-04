package com.plantpoppa.plant.resources;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/plant")
public class PlantResource {

    @GetMapping("/")
    String sendResponse() {
        return "Received";
    }
}
