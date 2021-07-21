package project.jpa.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import project.jpa.entity.Gender;
import project.jpa.entity.User;
import project.jpa.repository.UserRepository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    User user;
    List<User> users;

    @BeforeEach
    public void prepare() {
        MockitoAnnotations.initMocks(this);

        this.user = new User(0L,
                "thkim",
                "password",
                "태현",
                "0000000000",
                "dd@gmail.com",
                LocalDate.of(1989, 02, 27),
                Gender.male
        );

        this.users = new ArrayList<>();
        users.add(user);
    }



    @Test
    void 회원_조회(){
        Optional<User> otpUser = Optional.of(this.user);
        //테스트 하기 위해서 mock 객체를 설정함.
        Mockito.when(userRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(otpUser);

        User findUser = userService.findUser(user.getId());

        Assertions.assertEquals(this.user,findUser);
        Mockito.verify(userRepository).findById(this.user.getId());

    }

    @Test
    void 회원가입(){
        //given
        List<User> user2 = new ArrayList<>();
        Mockito.when(userRepository.findByPersonalId(ArgumentMatchers.anyString()))
                .thenReturn(user2);
        Mockito.when(userRepository.save(ArgumentMatchers.any()))
                .thenReturn(user);


        //when
        userService.join(user);

        //then
        Mockito.verify(userRepository).save(this.user);

    }

    @Test
    void 회원가입_중복(){
        Mockito.when(userRepository.findByPersonalId(ArgumentMatchers.anyString()))
                .thenReturn(this.users);

        Assertions.assertThrows(IllegalStateException.class,()->userService.join(user));

        Mockito.verify(userRepository).findByPersonalId(this.user.getPersonalId());
        Mockito.verify(userRepository, Mockito.times(0)).save(ArgumentMatchers.any());
    }

}
