package project.jpa.repository;


import project.jpa.dto.UserSearchCondition;
import project.jpa.entity.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> search(UserSearchCondition condition);
}
