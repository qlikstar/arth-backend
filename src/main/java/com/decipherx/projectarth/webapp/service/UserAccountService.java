package com.decipherx.projectarth.webapp.service;

import com.decipherx.projectarth.webapp.domain.Account;
import com.decipherx.projectarth.webapp.domain.User;
import com.decipherx.projectarth.webapp.exception.ValidationException;
import com.decipherx.projectarth.webapp.util.Sid;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface UserAccountService {

    CompletableFuture<Optional<User>>  getUserByUserSid(Sid userSid);

    User createUser(User user);

    Account createAccount(Sid userSid, Account account) throws ValidationException;

    Set<Account> getAllAccounts(Sid userSid);

}
