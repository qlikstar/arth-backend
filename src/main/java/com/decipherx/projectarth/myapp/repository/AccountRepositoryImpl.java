package com.decipherx.projectarth.myapp.repository;

import com.decipherx.projectarth.myapp.domain.Account;
import com.decipherx.projectarth.myapp.domain.User;
import com.decipherx.projectarth.myapp.enums.Broker;
import com.decipherx.projectarth.myapp.enums.Environment;
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Singleton
public class AccountRepositoryImpl implements AccountRepository{

    @PersistenceContext
    private EntityManager entityManager;

    public AccountRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Account save(@NotNull User user, @NotBlank Broker broker, @NotBlank Environment brokerEnv,
                        @NotBlank String brokerUrl, @NotBlank String brokerApiKey, @NotBlank String brokerApiSecret) {

        Account account = new Account(broker, brokerEnv, brokerUrl, brokerApiKey, brokerApiSecret);
        account.setUser(user);
        entityManager.persist(account);
        return account;
    }
}
