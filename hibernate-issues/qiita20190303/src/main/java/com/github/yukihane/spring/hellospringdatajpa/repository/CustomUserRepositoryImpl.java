package com.github.yukihane.spring.hellospringdatajpa.repository;

import com.github.yukihane.spring.hellospringdatajpa.entity.UserEntity;
import com.github.yukihane.spring.hellospringdatajpa.entity.UserEntity_;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomUserRepositoryImpl implements CustomUserRepository {

    @Autowired
    private EntityManager em;

    @Override
    public int deletByBasedateRange(final Date start, final Date end) {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaDelete<UserEntity> cd = cb.createCriteriaDelete(UserEntity.class);

        final Root<UserEntity> user = cd.from(UserEntity.class);
        cd.where(cb.between(user.get(UserEntity_.basedate), start, end));

        final Query q = em.createQuery(cd);
        return q.executeUpdate();
    }
}
