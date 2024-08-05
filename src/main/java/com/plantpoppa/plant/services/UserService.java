package com.plantpoppa.plant.services;

import com.plantpoppa.plant.dao.UserRepository;
import com.plantpoppa.plant.models.UserEntity;
import com.plantpoppa.plant.models.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

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
}
