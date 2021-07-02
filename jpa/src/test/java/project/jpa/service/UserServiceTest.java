package project.jpa.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.jpa.entity.Gender;
import project.jpa.entity.User;

import java.time.LocalDate;
import java.util.List;


@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void 회원가입(){

        //given
        User user1 = new User("thkim2","password","태현","01099841803","dirtysmile@naver.com", LocalDate.of(1989,02,27), Gender.male);

        //when
        userService.join(user1);


        //then
        List<User> users = userService.userList();

        Assertions.assertEquals(users.size(),1);
    }

    @Test
    public void 중복가입_실패(){
        //given
        User user1 = new User("thkim","password","태현","01099841803","dirtysmile@naver.com", LocalDate.of(1989,02,27), Gender.male);
        User user2 = new User("thkim","password","태현","01099841803","dirtysmile@naver.com", LocalDate.of(1989,02,27), Gender.male);

        //when
        userService.join(user1);

        Assertions.assertThrows(IllegalStateException.class,()->userService.join(user2));
    }

}