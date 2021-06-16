package project.jpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.jpa.entity.User;
import project.jpa.repository.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long join(User user){
        validateDublicatePersionalId(user);

        return userRepository.save(user).getId();
    }

    private void validateDublicatePersionalId(User user) {
        userRepository.fin
    }

}
