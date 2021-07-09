package project.jpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.jpa.entity.Gender;
import project.jpa.entity.User;
import project.jpa.service.UserService;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

@Profile("local")
@Component
@RequiredArgsConstructor
public class InitUser {
    private final InitUserService initUserService;

    @PostConstruct
    public void init(){
        initUserService.init();

    }

    @Component
    static class InitUserService {
        @PersistenceContext
        private EntityManager em;


        @Transactional
        public void init(){

            User user1 = new User("admin","password","administrator","01099841803","dirtysmile@naver.com", LocalDate.of(1989,02,27), Gender.male);
            em.persist(user1);

            int year = 0;

            for(int i=0; i<50; i++){
                User user = new User("thkim"+i,"password","태현"+i,"01099841803","dirtysmile@naver.com", LocalDate.of(1950+i,02,27), Gender.male);
                em.persist(user);
            }

        }
    }
}
