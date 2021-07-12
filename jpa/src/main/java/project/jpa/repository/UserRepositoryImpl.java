package project.jpa.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<User> searchPageSimple(UserSearchCondition condition, Pageable pageable) {

        QueryResults<User> results = queryFactory
                .selectFrom(user)
                .where(
                        usernameEq(condition.getName()),
                        ageGoe(condition.getAgeGoe()),
                        ageLoe(condition.getAgeLoe())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<User> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable,total);
    }

    @Override
    public Page<User> searchPageComplex(UserSearchCondition condition, Pageable pageable) {
        return null;
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
