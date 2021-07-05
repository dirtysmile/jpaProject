package project.jpa.initData;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.jpa.entity.Gender;
import project.jpa.entity.User;
import project.jpa.service.UserService;

import java.time.LocalDate;

@Transactional
@RequiredArgsConstructor
@Service
public class DataService {

    private final UserService userService;

    public void insertUsers(){
        User user1 = new User("admin","password","administrator","01099841803","dirtysmile@naver.com", LocalDate.of(1989,02,27), Gender.male);
        userService.join(user1);
        for(int i=0; i<50; i++){
            User user = new User("thkim"+i,"password","태현"+i,"01099841803","dirtysmile@naver.com", LocalDate.of(1989,02,27), Gender.male);
            userService.join(user);
        }


    }


}
