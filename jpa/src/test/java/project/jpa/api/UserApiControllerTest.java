package project.jpa.api;

import org.h2.engine.UserBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import project.jpa.entity.Gender;
import project.jpa.entity.User;
import project.jpa.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserApiControllerTest {


    @InjectMocks
    UserApiController userApiController;

    @Mock
    UserService userService;

    @Autowired
    private MockMvc mockMvc;


    User user;
    List<User> users;

    @BeforeEach
    void prepare(){

        user = new User(1L,"admin","password","administrator","01000000000",
                "eee@gmail.com", LocalDate.of(1989,02,27), Gender.male);
        users = new ArrayList<>();
        users.add(user);
    }


    @Test
    void 사용자_리스트() throws Exception{
        Mockito.when(userService.findUsers())
                .thenReturn(users);

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andDo(print());

    }
}