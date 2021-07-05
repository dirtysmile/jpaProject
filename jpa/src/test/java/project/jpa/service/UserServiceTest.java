package project.jpa.service;

import org.aspectj.lang.annotation.Before;
import org.flywaydb.test.FlywayTestExecutionListener;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import project.jpa.entity.Gender;
import project.jpa.entity.User;
import project.jpa.initData.DataService;

import java.time.LocalDate;
import java.util.List;


@SpringBootTest
@Transactional
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class })
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    DataService dataService;

    @BeforeEach
    public void setup() {
        dataService.insertUsers();
    }

    @Test
    public void 회원가입(){

        //given
        User user1 = new User("testSaveUser","password","태현","01099841803","dirtysmile@naver.com", LocalDate.of(1989,02,27), Gender.male);

        //when
        List<User> pre_userList = userService.userList();
        userService.join(user1);


        //then
        List<User> users = userService.userList();

        Assertions.assertEquals(users.size(),pre_userList.size()+1);
    }

    @Test
    public void 중복가입_실패(){
        //given
        User user1 = new User("test","password","태현","01099841803","dirtysmile@naver.com", LocalDate.of(1989,02,27), Gender.male);
        User user2 = new User("test","password","태현","01099841803","dirtysmile@naver.com", LocalDate.of(1989,02,27), Gender.male);

        //when
        userService.join(user1);

        Assertions.assertThrows(IllegalStateException.class,()->userService.join(user2));
    }

}