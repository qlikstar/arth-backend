package com.decipherx.projectarth.myapp.validation;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Introspected
public class AccountSaveCommand {

    @NotBlank
    private String brokerName;

    @NotBlank
    private String environment;

    @NotNull
    private String brokerUrl;

    @NotNull
    private String brokerApiKey;

    @NotNull
    private String brokerApiSecret;

    public AccountSaveCommand(@NotBlank String brokerName, @NotBlank String environment, @NotNull String brokerUrl,
                              @NotNull String brokerApiKey, @NotNull String brokerApiSecret) {
        this.brokerName = brokerName;
        this.environment = environment;
        this.brokerUrl = brokerUrl;
        this.brokerApiKey = brokerApiKey;
        this.brokerApiSecret = brokerApiSecret;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getBrokerUrl() {
        return brokerUrl;
    }

    public void setBrokerUrl(String brokerUrl) {
        this.brokerUrl = brokerUrl;
    }

    public String getBrokerApiKey() {
        return brokerApiKey;
    }

    public void setBrokerApiKey(String brokerApiKey) {
        this.brokerApiKey = brokerApiKey;
    }

    public String getBrokerApiSecret() {
        return brokerApiSecret;
    }

    public void setBrokerApiSecret(String brokerApiSecret) {
        this.brokerApiSecret = brokerApiSecret;
    }
}
