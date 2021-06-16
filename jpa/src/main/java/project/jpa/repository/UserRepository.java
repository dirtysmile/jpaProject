package project.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jpa.entity.User;

import java.util.List;

public interface UserRepository  extends JpaRepository<User,Long> {

    List<User> findByPersonal_id(String personal_id);
}
