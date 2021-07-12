package project.jpa.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.jpa.dto.UserSearchCondition;
import project.jpa.entity.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> search(UserSearchCondition condition);
    Page<User> searchPageSimple(UserSearchCondition condition, Pageable pageable);
    Page<User> searchPageComplex(UserSearchCondition condition, Pageable pageable);


}
