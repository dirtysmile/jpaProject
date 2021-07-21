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

    @Test
    public void saveUser(){

        //given
        List<User> before_user = repository.findAll();
        User u = new User("thkim","password","kim","01099841803"
                ,"dirtysmile89@naver.com", LocalDate.of(1989,02,27), Gender.male);

        //when
        repository.save(u);
        List<User> after_user = repository.findAll();

        //then
        Assertions.assertEquals(after_user.size(),before_user.size()+1);

    }

    @Test
    public void updateUser(){
        //given
        Optional<User> user = repository.findById(2L);
        User user1 = user.get();

        //when
        user1.updateUser("토스트");
        repository.save(user1);

        Optional<User> after_user = repository.findById(2L);
        User user2 = after_user.get();

        //then

        Assertions.assertEquals(user2.getName(),"토스트");


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