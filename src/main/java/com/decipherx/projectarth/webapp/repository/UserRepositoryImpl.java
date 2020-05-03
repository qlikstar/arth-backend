package com.decipherx.projectarth.webapp.repository;

import com.decipherx.projectarth.webapp.domain.Account;
import com.decipherx.projectarth.webapp.domain.User;
import com.decipherx.projectarth.webapp.util.Sid;
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Singleton
public class UserRepositoryImpl implements UserRepository{

    @PersistenceContext
    private EntityManager entityManager;
//    private final ApplicationConfiguration applicationConfiguration;

    public UserRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
        //this.applicationConfiguration = applicationConfiguration;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByUserSid(@NotNull final Sid userSid) {
        Query query = entityManager.createQuery("SELECT user FROM User user WHERE user.sid = :userSid");
        query.setParameter("userSid", userSid.getValue());
        try {
            return Optional.of((User) query.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public User save(@NotBlank final String username, @NotBlank final String password,
                     @NotBlank final String masterEmail) {
        User user = new User(username, password, masterEmail);
        entityManager.persist(user);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Account> getAllAccountsByUserSid(@NotNull final Sid userSid) {
        Query query = entityManager.createQuery("SELECT account FROM Account account WHERE account.user.sid = :userSid");
        query.setParameter("userSid", userSid.getValue());

        return (Set<Account>) query.getResultList().stream().collect(Collectors.toSet());

    }


}
