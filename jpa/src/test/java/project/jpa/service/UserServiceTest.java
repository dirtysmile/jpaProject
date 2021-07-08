package project.jpa.service;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.aspectj.lang.annotation.Before;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import project.jpa.entity.Gender;
import project.jpa.entity.QUser;
import project.jpa.entity.User;
import project.jpa.initData.DataService;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static project.jpa.entity.QUser.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    DataService dataService;

    @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory;

    @BeforeEach
    public void setup() {
         queryFactory = new JPAQueryFactory(em);
//        dataService.insertUsers();
    }


    @Test
    public void 회원가입(){

        //given
        User user1 = new User("testSaveUser","password","태현","01099841803","dirtysmile@naver.com", LocalDate.of(1989,02,27), Gender.male);

        //when
        List<User> pre_userList = userService.findUsers();
        userService.join(user1);


        //then
        List<User> users = userService.findUsers();

        Assertions.assertEquals(users.size(),pre_userList.size()+1);
    }

    @Test
    public void 중복가입_실패(){
        //given
        User user1 = new User("test","password","태현","01099841803","dirtysmile@naver.com", LocalDate.of(1989,02,27), Gender.male);
        User user2 = new User("test","password","태현","01099841803","dirtysmile@naver.com", LocalDate.of(1989,02,27), Gender.male);

        //when
        userService.join(user1);

        //then
        Assertions.assertThrows(IllegalStateException.class,()->userService.join(user2));
    }


    @Test
    public void 회원_조회_실패(){
        //given
        Long id = 100L;


        //when


        //then
        Assertions.assertThrows(NoSuchElementException.class,() -> userService.findUser(id));
    }

    @Test
    public void startQuerydsl(){
        //given
//        QUser m = QUser.user;

        //when
        User findUser = queryFactory.select(user)
                .from(user)
                .where(user.name.eq("administrator"))
                .fetchOne();

        //then
        Assertions.assertEquals(findUser.getName(),"administrator");
    }

    @Test
    public void search(){
        User findUser = queryFactory.selectFrom(user)
                .where(user.name.eq("administrator")
                        .and(user.gender.eq(Gender.male)))
                .fetchOne();

        //then
        Assertions.assertEquals(findUser.getName(),"administrator");

    }


    @Test
    public void search2(){
        // and 변경
        User findUser = queryFactory.selectFrom(user)
                .where(
                        user.name.eq("administrator"),
                        user.gender.eq(Gender.male)
                )
                .fetchOne();

        //then
        Assertions.assertEquals(findUser.getName(),"administrator");

    }

    @Test
    public void resultFetch(){
        List<User> fetch = queryFactory
                .selectFrom(user)
                .fetch();

        User fetchOne = queryFactory
                .selectFrom(QUser.user)
                .fetchOne();

        User fetchFirst = queryFactory
                .selectFrom(QUser.user)
                .fetchFirst();

        QueryResults<User> fetchResults = queryFactory
                .selectFrom(user)
                .fetchResults();

        fetchResults.getTotal();
        List<User> content = fetchResults.getResults();

        long count = queryFactory
                .selectFrom(user)
                .fetchCount();




    }

}