package project.jpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import project.jpa.entity.User;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @Test
    @Rollback(false)
    public void saveUser(){
        User u = new User();

        u.setName("김태현");
        repository.save(u);

    }

}