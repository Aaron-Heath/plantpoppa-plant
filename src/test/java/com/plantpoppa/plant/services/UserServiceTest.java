package com.plantpoppa.plant.services;

import com.plantpoppa.plant.dao.UserRepository;
import com.plantpoppa.plant.models.UserEntity;
import com.plantpoppa.plant.models.dto.UserDto;
import com.plantpoppa.plant.security.CustomUserDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
public class UserServiceTest {

    @Mock
    UserRepository mockUserRepository;

    @InjectMocks
    UserService userService;

    @Captor
    ArgumentCaptor<UserEntity> userCaptor;


    private UserEntity user;

    private CustomUserDetails userDetails;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        user = new UserEntity.UserBuilder()
                .email("test.user@email.com")
                .firstname("Test")
                .lastname("User")
                .pw_hash("securePassword")
                .phone("215-555-5555")
                .zip("19038")
                .build();

        userDetails = new CustomUserDetails(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getUuid(),
                user.getRole());
    }

    @Test
    public void givenFirstNameLastAndPhone_UpdatesThoseParams() {
        UserDto mockOutput = user.toDto();
        mockOutput.setFirstname("Json");
        mockOutput.setLastname("Bourne");
        mockOutput.setPhone("215-924-7605");

        UserEntity expected = new UserEntity.UserBuilder()
                        .firstname(mockOutput.getFirstname())
                        .lastname(mockOutput.getLastname())
                        .phone(mockOutput.getPhone())
                        .pw_hash(user.getPassword())
                        .uuid(user.getUuid())
                        .user_id(user.getId())
                        .email(user.getEmail())
                        .build();

        when(mockUserRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(mockUserRepository.saveAndFlush(ArgumentMatchers.any())).thenReturn(expected);

        UserDto userDto = new UserDto.UserDtoBuilder()
                .firstname("Json")
                .lastname("Bourne")
                .phone("215-924-7605")
                .build();

        userService.updateUser(userDetails, userDto);

        verify(mockUserRepository).saveAndFlush(userCaptor.capture());

        UserEntity actual = userCaptor.getValue();
        Assertions.assertEquals(expected, actual);
    }


}
