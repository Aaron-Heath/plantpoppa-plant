package com.plantpoppa.plant.resources;

import com.plantpoppa.plant.models.UserEntity;
import com.plantpoppa.plant.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserResource {
    private final UserService userService;

    @Autowired
    UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    ResponseEntity<?> findAll() {
        List<UserEntity> users = userService.findAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
