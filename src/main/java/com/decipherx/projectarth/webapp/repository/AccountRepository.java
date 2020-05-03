package com.decipherx.projectarth.webapp.repository;

import com.decipherx.projectarth.webapp.domain.Account;
import com.decipherx.projectarth.webapp.domain.User;
import com.decipherx.projectarth.webapp.enums.Broker;
import com.decipherx.projectarth.webapp.enums.Environment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface AccountRepository {

    Account save(@NotNull User user, @NotBlank Broker broker, @NotBlank Environment brokerEnv,
                 @NotBlank String brokerUrl, @NotBlank String brokerApiKey, @NotBlank String brokerApiSecret);


}
