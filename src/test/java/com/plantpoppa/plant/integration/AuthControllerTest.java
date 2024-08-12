//package com.plantpoppa.plant.integration;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.plantpoppa.plant.dao.UserRepository;
//import com.plantpoppa.plant.models.UserEntity;
//import com.plantpoppa.plant.models.dto.AuthResponseDto;
//import com.plantpoppa.plant.models.dto.CreateUserDto;
//import com.plantpoppa.plantg.models.dto.LoginDto;
//import jakarta.servlet.ServletContext;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.mock.web.MockServletContext;
//import org.springframework.test.annotation.Commit;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MockMvcResultMatchersDsl;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
//@SpringBootTest
//@ActiveProfiles("test")
//public class AuthControllerTest {
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//    private MockMvc mockMvc;
//
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private final UserEntity user = new UserEntity.UserBuilder()
//            .email("test.user@email.com")
//            .firstname("Test")
//            .lastname("User")
//            .pw_hash("securePassword")
//            .phone("215-555-5555")
//            .zip("19038")
//            .build();
//
//
//    @BeforeEach
//    public void setup() throws Exception {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
//    }
//
//    //https://www.baeldung.com/integration-testing-in-spring - Testing that the context is correctly configured and populated
//    @Test
//    public void givenWac_whenServletContext_thenItProvidesAuthController() {
//        ServletContext servletContext = webApplicationContext.getServletContext();
//
//        assertNotNull(servletContext);
//        assertTrue(servletContext instanceof MockServletContext);
//        assertNotNull(webApplicationContext.getBean("authResource"));
//    }
//
//    @Test
//    @Commit
//    public void givenCreateUserDto_CreatesUser() throws Exception {
//        long initialUserCount = userRepository.count();
//        CreateUserDto userDto = new CreateUserDto();
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setFirstName(user.getFirstName());
//        userDto.setLastName(user.getLastName());
//        userDto.setZip(user.getZip());
//        userDto.setPhone(user.getPhone());
//
//        MvcResult mvcResult =  mockMvc.perform(post("/api/auth/register")
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(userDto)))
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andReturn();
//        long expectedUserCount = initialUserCount + 1;
//        long actualUserCount = userRepository.count();
//        assertEquals(expectedUserCount, actualUserCount);
//    }
//
//    @Test
//    public void givenLoginCredentials_ReturnsToken() throws Exception {
//        LoginDto loginDto = new LoginDto();
//        loginDto.setEmail(user.getEmail());
//        loginDto.setPassword(user.getPassword());
//        System.out.println(userRepository.count());
//
//        MvcResult mvcResult = mockMvc.perform(post("/api/auth/login")
//                .contentType("application/json")
//                .content(objectMapper.writeValueAsString(loginDto)))
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//
//        AuthResponseDto responseDto = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), AuthResponseDto.class);
//        String expectedType = "Bearer ";
//        assertEquals(responseDto.getType(), expectedType);
//    }
//}
