package project.jpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.jpa.entity.User;
import project.jpa.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long join(User user){
        validateDublicatePersionalId(user);

        return userRepository.save(user).getId();
    }

    public User findUser(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new NoSuchElementException("존재하지 않는 사용자 입니다."));
    }

    @Transactional(readOnly = true)
    public List<User> findUsers(){
        return userRepository.findAll();
    }



    private void validateDublicatePersionalId(User user) {
        List<User> users = userRepository.findByPersonalId(user.getPersonalId());
        if(!users.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }


    }

}
