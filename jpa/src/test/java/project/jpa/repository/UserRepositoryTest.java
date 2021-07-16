package project.jpa.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import project.jpa.dto.UserSearchCondition;
import project.jpa.entity.Gender;
import project.jpa.entity.User;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @Autowired
    EntityManager em;

    @Test
    @Rollback(false)
    public void saveUser(){
        User u = new User("thkim","password","kim","01099841803"
                ,"dirtysmile89@naver.com", LocalDate.of(1989,02,27), Gender.male);

        repository.save(u);

    }

    @Test
    @Rollback(false)
    public void updateUser(){
        Optional<User> user = repository.findById(2L);
        System.out.println(user.get().getName());
        User user1 = user.get();
        user1.setName("토스트1");

        repository.save(user1);


    }

    @Test
    public void searchTest(){
        UserSearchCondition condition = new UserSearchCondition();
        condition.setAgeGoe(10);
        condition.setAgeLoe(80);
        condition.setName("태현0");

        List<User> search = repository.search(condition);

        System.out.println(search.size());

    }

    @Test
    public void searchPageSimpleTest(){
        UserSearchCondition condition = new UserSearchCondition();

        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<User> result = repository.searchPageSimple(condition, pageRequest);

        Assertions.assertEquals(result.getSize(),3);

    }
}