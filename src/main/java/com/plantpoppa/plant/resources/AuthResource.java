package com.plantpoppa.plant.resources;

import com.plantpoppa.plant.models.UserEntity;
import com.plantpoppa.plant.models.dto.AuthResponseDto;
import com.plantpoppa.plant.models.dto.CreateUserDto;
import com.plantpoppa.plant.models.dto.LoginDto;
import com.plantpoppa.plant.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthResource {

    private AuthService authService;
    private UserResource userResource;

    @Autowired
    public AuthResource(AuthService authService, UserResource userResource) {

        this.authService = authService;
        this.userResource = userResource;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        AuthResponseDto authResponse = authService.login(loginDto);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CreateUserDto createUserDto) {
        UserEntity createdUser = authService.createUser(createUserDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
