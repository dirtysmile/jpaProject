package project.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jpa.entity.User;

public interface UserRepository  extends JpaRepository<User,Long> {

}
