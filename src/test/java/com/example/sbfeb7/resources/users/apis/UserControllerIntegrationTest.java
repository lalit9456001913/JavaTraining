package com.example.sbfeb7.resources.users.apis;

import com.example.sbfeb7.factory.DataFactory;
import com.example.sbfeb7.resources.users.entities.User;
import com.example.sbfeb7.resources.users.repos.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class UserControllerIntegrationTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    @Autowired
    UserRepository userRepository;
    private User user;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        User build = DataFactory.user().build();
        System.out.println(build);
        user = userRepository.save(build);
    }

    @Test
    void getUserById() throws Exception {
        mockMvc.perform(get("/users/%s".formatted(user.getId()))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @AfterEach
    public void afterEach() {
        userRepository.deleteById(user.getId());
    }


}

