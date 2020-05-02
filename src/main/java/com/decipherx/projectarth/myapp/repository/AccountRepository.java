package com.decipherx.projectarth.myapp.repository;

import com.decipherx.projectarth.myapp.domain.Account;
import com.decipherx.projectarth.myapp.domain.User;
import com.decipherx.projectarth.myapp.enums.Broker;
import com.decipherx.projectarth.myapp.enums.Environment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface AccountRepository {

    Account save(@NotNull User user, @NotBlank Broker broker, @NotBlank Environment brokerEnv,
                 @NotBlank String brokerUrl, @NotBlank String brokerApiKey, @NotBlank String brokerApiSecret);


}
