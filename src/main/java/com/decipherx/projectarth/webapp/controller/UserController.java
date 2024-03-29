package com.decipherx.projectarth.webapp.controller;

import com.decipherx.projectarth.webapp.domain.Account;
import com.decipherx.projectarth.webapp.domain.User;
import com.decipherx.projectarth.webapp.enums.Broker;
import com.decipherx.projectarth.webapp.enums.Environment;
import com.decipherx.projectarth.webapp.exception.NotFoundException;
import com.decipherx.projectarth.webapp.exception.ValidationException;
import com.decipherx.projectarth.webapp.service.UserAccountService;
import com.decipherx.projectarth.webapp.util.Sid;
import com.decipherx.projectarth.webapp.validation.AccountSaveCommand;
import com.decipherx.projectarth.webapp.validation.UserSaveCommand;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Controller("/users")
public class UserController {

    protected final UserAccountService userAccountService;

    public UserController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Post
    public HttpResponse<User> createUser(@Body @Valid UserSaveCommand cmd) {
        User user = new User(cmd.getUsername(), cmd.getPassword(), cmd.getMasterEmail());
        User savedUser = userAccountService.createUser(user);

        return HttpResponse
                .created(savedUser)
                .headers(headers -> headers.location(location(user.getSid())));
    }

    @Get("/{userSid}")
    public CompletableFuture<HttpResponse<Optional<User>>> findByUserSid (String userSid) throws ValidationException {

        Sid.isValidSid(userSid);
        return userAccountService
                .getUserByUserSid(new Sid(userSid))
                .thenApply(maybeUser -> {
                    if (maybeUser.isEmpty()) {
                        throw new CompletionException(new NotFoundException(String.format("Sid not found : %s", userSid)));
                    }
                    return HttpResponse.ok(maybeUser);
                });
        //TODO: FIX -> This responds 500 on NotFoundException
    }

    @Post("/{userSid}/accounts")
    public Account createAccount(String userSid, @Body @Valid AccountSaveCommand command) throws ValidationException {
        Sid.isValidSid(userSid);
        return userAccountService.createAccount(Sid.convertToSid(userSid), convertAccountSaveCommandToAccount(command));
    }

    @Get("/{userSid}/accounts")
    public Set<Account> getAllAccounts(String userSid) throws ValidationException {
        Sid.isValidSid(userSid);
        return userAccountService.getAllAccountsByUser(Sid.convertToSid(userSid));
    }

    protected URI location(String userSid) {
        return URI.create("/users/" + userSid);
    }

    protected URI location(User user) {
        return location(user.getSid());
    }

    private static Account convertAccountSaveCommandToAccount(AccountSaveCommand command) throws ValidationException {

        try {
            return new Account(Broker.valueOf(command.getBrokerName()), Environment.valueOf(command.getEnvironment()),
                    command.getBrokerUrl(), command.getBrokerApiKey(), command.getBrokerApiSecret());

        }catch (IllegalArgumentException ex) {
            //TODO: Add which field out of (Broker/Env) is invalid. Also, suggest list of valid values
            throw new ValidationException("Invalid value : " + ex.getMessage());
        }
    }
}
