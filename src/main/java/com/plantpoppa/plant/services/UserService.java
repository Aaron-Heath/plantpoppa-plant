package com.plantpoppa.plant.services;

import com.plantpoppa.plant.dao.UserRepository;
import com.plantpoppa.plant.models.UserEntity;
import com.plantpoppa.plant.models.dto.UserDto;
import com.plantpoppa.plant.security.CustomUserDetails;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<UserDto> findAll() {
        List<UserEntity> rawUsers = userRepository.findAll();
        // Convert raw users to Dtos to remove sensitive information before being returned.
        List<UserDto> userDtos =  new ArrayList<>();
        rawUsers.forEach(user -> userDtos.add(user.toDto()));
        return userDtos;
    }

    public long count() {
        return userRepository.count();
    }

    public Optional<UserEntity> findAuthenticatedUser(CustomUserDetails userDetails) {
        int userId = userDetails.getUserId();
        return userRepository.findById(userId);
    };

    public Optional<UserDto> updateUser(CustomUserDetails userDetails, UserDto userDto) {
        Optional<UserEntity> authenticatedUser = findAuthenticatedUser(userDetails);
        if(authenticatedUser.isEmpty()) return Optional.empty();

        UserEntity user = authenticatedUser.get();

        if(!(userDto.getFirstname() == null) && !userDto.getFirstname().isBlank()) {
            user.setFirstName(userDto.getFirstname());
        }

        if(!(userDto.getLastname() == null) && !userDto.getLastname().isBlank()) {
            user.setLastName(userDto.getLastname());
        }

        if(!(userDto.getPhone() == null) && !userDto.getPhone().isBlank()) {
            user.setPhone(userDto.getPhone());
        }

        if(!(userDto.getZip() == null) && !userDto.getZip().isBlank()) {
            user.setZip(userDto.getZip());
        }
        return Optional.of(userRepository.saveAndFlush(user).toDto());
    }

    public Optional<UserDto> removePhone(CustomUserDetails userDetails) {
        Optional<UserEntity> optionalUser = findAuthenticatedUser(userDetails);

        if(optionalUser.isEmpty()) {
            return Optional.empty();
        }

        UserEntity user = optionalUser.get();

        user.setPhone(null);

        return (Optional.of(userRepository.saveAndFlush(user).toDto()));
    }

    public Optional<UserDto> removeZip(CustomUserDetails userDetails) {
        Optional<UserEntity> optionalUser = findAuthenticatedUser(userDetails);

        if (optionalUser.isEmpty()) return Optional.empty();

        UserEntity user = optionalUser.get();

        user.setZip(null);

        return (Optional.of(userRepository.saveAndFlush(user).toDto()));
    }

    public void deleteMe(CustomUserDetails userDetails) {
        userRepository.deleteById(userDetails.getUserId());
    }
}
