package project.jpa.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.StringUtils;
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
                .where(
                        usernameEq(condition.getName()),
                        ageGoe(condition.getAgeGoe()),
                        ageLoe(condition.getAgeLoe())
                )
                .fetch();
        return fetch;
    }



    private BooleanExpression usernameEq(String username){
        return StringUtils.hasText(username) ? user.name.eq(username) : null;
    }

    private BooleanExpression ageGoe(Integer ageGoe) {
        return ageGoe !=null ? user.age.goe(ageGoe): null;
    }

    private BooleanExpression ageLoe(Integer ageLoe) {
        return ageLoe !=null ? user.age.loe(ageLoe): null;
    }
}
