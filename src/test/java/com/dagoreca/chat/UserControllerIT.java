package com.dagoreca.chat;

import com.dagoreca.chat.domain.User;
import com.dagoreca.chat.repository.UserRepository;
import com.dagoreca.chat.service.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.runner.RunWith;
import org.junit.Test;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = SuperChatApplication.class)
public class UserControllerIT {

    @Autowired
    private MockMvc userMockMvc;

    @Autowired
    UserRepository userRepository;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @WithMockUser
    public void testRegister() throws Exception {
        UserDTO user = new UserDTO();
        user.setLogin("test_user");
        user.setPassword("test_user");
        user.setFirstName("test_user");

        userMockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isCreated());

        Optional<User> optionalUser  = userRepository.findByLogin("test_user");
        assertThat(optionalUser.isPresent());
    }

    @Test
    @WithMockUser
    public void updateRegisteredUser() throws Exception {
        UserDTO user = new UserDTO();
        user.setPassword("t");
        user.setFirstName("test_user");

        userMockMvc.perform(put("/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isCreated());

        Optional<User> optionalUser  = userRepository.findByLogin("test_user");
        assertThat(optionalUser.isPresent());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testTakeUsers() throws Exception {
        userMockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
