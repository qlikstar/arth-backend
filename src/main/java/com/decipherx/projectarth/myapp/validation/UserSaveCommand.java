package com.decipherx.projectarth.myapp.validation;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotNull;

@Introspected
public class UserSaveCommand {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String masterEmail;

    public UserSaveCommand(String username, String password, String masterEmail) {
        this.username = username;
        this.password = password;
        this.masterEmail = masterEmail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getMasterEmail() {
        return masterEmail;
    }

    public void setMasterEmail(final String masterEmail) {
        this.masterEmail = masterEmail;
    }
}
