package com.example.oauth2server.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.example.oauth2server.entity.Task;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomizedTaskRepositoryImpl implements CustomizedTaskRepository {

    private final EntityManager entityManager;

    @Override
    public List<Task> findByUserName(final String name) {
        final TypedQuery<Task> q = entityManager.createQuery("select t from Task t where t.creator.name = :name",
            Task.class);
        q.setParameter("name", name);
        return q.getResultList();
    }

    @Override
    public Optional<Task> findByIdAndUserName(final Long id, final String name) {
        final TypedQuery<Task> q = entityManager
            .createQuery("select t from Task t where t.id = :id and t.creator.name = :name", Task.class);
        q.setParameter("id", id);
        q.setParameter("name", name);
        try {
            final Task ret = q.getSingleResult();
            return Optional.of(ret);
        } catch (final NoResultException e) {
            return Optional.empty();
        }
    }

}
