package project.jpa.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import project.jpa.dto.UserSearchCondition;
import project.jpa.entity.QUser;
import project.jpa.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

import static project.jpa.entity.QUser.user;

public class UserRepositoryImpl implements UserRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<User> search(UserSearchCondition condition) {
        List<User> fetch = queryFactory
                .selectFrom(user)
                .where(user.name.eq("administor"))
                .fetch();
        return null;
    }
}
