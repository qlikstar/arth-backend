package com.decipherx.projectarth.webapp.repository;

import com.decipherx.projectarth.webapp.domain.Account;
import com.decipherx.projectarth.webapp.domain.User;
import com.decipherx.projectarth.webapp.util.Sid;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;

public interface UserRepository {
    Optional<User> findByUserSid(@NotNull Sid userSid);

    User save(@NotBlank String username, @NotBlank String password, @NotBlank String masterEmail);

    Set<Account> getAllAccountsByUserSid(@NotNull final Sid userSid);
}
