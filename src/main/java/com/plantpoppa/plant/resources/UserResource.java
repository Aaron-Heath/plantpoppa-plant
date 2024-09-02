package com.plantpoppa.plant.resources;

import com.plantpoppa.plant.models.UserEntity;
import com.plantpoppa.plant.models.dto.UserDto;
import com.plantpoppa.plant.security.CustomUserDetails;
import com.plantpoppa.plant.services.UserService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
        List<UserDto> users = userService.findAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/count")
    long count() {
        return userService.count();
    }

    @GetMapping("/me")
    ResponseEntity<?> getMe( ServletRequest request,
            ServletResponse response,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        Optional<UserEntity> userEntity = userService.findAuthenticatedUser(userDetails);

        if(userEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No authenticated user found.");
        UserDto userDto = userEntity.get().toDto();

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PatchMapping("/me")
    ResponseEntity<?> updateUser(ServletRequest request,
            ServletResponse response,
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody UserDto userDto) {
        Optional<UserDto> updatedUser = userService.updateUser(userDetails, userDto);

        if(updatedUser.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Authenticated User not found");

        return new ResponseEntity<>(updatedUser.get(),HttpStatus.OK);


    }

    @DeleteMapping("/me")
    ResponseEntity<?> deleteMe(@AuthenticationPrincipal CustomUserDetails userDetails) {
        userService.deleteMe(userDetails);

        HashMap<String, String> responseBody = new HashMap<>();

        responseBody.put("message", "User deleted.");

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
