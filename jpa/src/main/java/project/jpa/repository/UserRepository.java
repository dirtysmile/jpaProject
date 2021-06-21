package project.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jpa.entity.User;

import java.util.List;

public interface UserRepository  extends JpaRepository<User,Long> {

    List<User> findByPersonalId(String personalId);
    List<User> findByName(String name);
}
