package com.decipherx.projectarth.myapp.service;

import com.decipherx.projectarth.myapp.domain.Account;
import com.decipherx.projectarth.myapp.domain.User;
import com.decipherx.projectarth.myapp.exception.ValidationException;
import com.decipherx.projectarth.myapp.util.Sid;

import java.util.Optional;
import java.util.Set;

public interface UserAccountService {

    Optional<User>  getUserByUserSid(Sid userSid);

    User createUser(User user);

    Account createAccount(Sid userSid, Account account) throws ValidationException;

    Set<Account> getAllAccounts(Sid userSid);

}
