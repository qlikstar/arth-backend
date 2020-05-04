package com.decipherx.projectarth.webapp.service;

import com.decipherx.projectarth.webapp.domain.Account;
import com.decipherx.projectarth.webapp.domain.User;
import com.decipherx.projectarth.webapp.exception.ValidationException;
import com.decipherx.projectarth.webapp.repository.AccountRepository;
import com.decipherx.projectarth.webapp.repository.UserRepository;
import com.decipherx.projectarth.webapp.util.Sid;

import javax.inject.Singleton;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Singleton
public class UserAccountServiceImpl implements UserAccountService{

    private UserRepository userRepository;
    private AccountRepository accountRepository;

    public UserAccountServiceImpl(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public CompletableFuture<Optional<User>> getUserByUserSid(Sid userSid) {
        return CompletableFuture.completedFuture(userRepository.findByUserSid(userSid));
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user.getUsername(), user.getPassword(), user.getMasterEmail());
    }

    @Override
    public Account createAccount(Sid userSid, Account account) throws ValidationException {
        Optional<User> mayBeUser = userRepository.findByUserSid(userSid);
        if (mayBeUser.isPresent()) {
            return accountRepository.save(mayBeUser.get(), account.getBroker(), account.getBrokerEnv(),
                    account.getBrokerUrl(), account.getBrokerApiKey(), account.getBrokerApiSecret());
        } else {
            throw new ValidationException("User not found : " + userSid.getValue());
        }
    }

    @Override
    public Set<Account> getAllAccountsByUser(Sid userSid) {
        return userRepository.getAllAccountsByUserSid(userSid);
    }

    @Override
    public Set<Account> getAllActiveAccounts() {
        return userRepository.getAllActiveAccounts();
    }

}
