package com.plantpoppa.plant.services;

import com.plantpoppa.plant.dao.UserRepository;
import com.plantpoppa.plant.models.UserEntity;
import com.plantpoppa.plant.models.dto.AuthResponseDto;
import com.plantpoppa.plant.models.dto.CreateUserDto;
import com.plantpoppa.plant.models.dto.LoginDto;
import com.plantpoppa.plant.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private TokenGenerator tokenGenerator;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenGenerator tokenGenerator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenGenerator = tokenGenerator;
    }

    public UserEntity createUser(CreateUserDto createUserDto) {
        UserEntity user = createUserDto.toUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
        return user;
    }


    public AuthResponseDto login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginDto.getEmail(),
                loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenGenerator.generateToken(authentication);

        return new AuthResponseDto(token);

    }
}
