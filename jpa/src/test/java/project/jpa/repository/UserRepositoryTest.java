package project.jpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import project.jpa.entity.Gender;
import project.jpa.entity.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @Test
    @Rollback(false)
    public void saveUser(){
        User u = new User("thkim","password","kim","01099841803"
                ,"dirtysmile89@naver.com", LocalDate.of(1989,02,27), Gender.male);

        repository.save(u);

    }

}