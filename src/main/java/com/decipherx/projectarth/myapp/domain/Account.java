package com.decipherx.projectarth.myapp.domain;

import com.decipherx.projectarth.myapp.enums.Broker;
import com.decipherx.projectarth.myapp.enums.Environment;
import com.decipherx.projectarth.myapp.util.SidPrefix;
import com.decipherx.projectarth.myapp.util.UUIDGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Table
@Entity
public class Account implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid_seq")
    @GenericGenerator(
            name = "uuid_seq",
            strategy = "com.decipherx.projectarth.myapp.util.UUIDGenerator",
            parameters = {@org.hibernate.annotations.Parameter(
                    name = UUIDGenerator.VALUE_PREFIX_PARAMETER, value = SidPrefix.ACCOUNT)}
    )
    @Column(name = "sid")
    private String Sid;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "broker_name", nullable = false)
    private Broker broker;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "broker_env")
    private Environment brokerEnv;

    @NotNull
    @Column(name = "broker_url", nullable = false)
    private String brokerUrl;

    @NotNull
    @Column(name = "broker_api_key", nullable = false, unique = true)
    private String brokerApiKey;


    @Column(name = "broker_api_secret", nullable = false, unique = true)
    private String brokerApiSecret;

    @JsonIgnore
    @ManyToOne
    private User user;

    public Account() {
    }

    public Account(@NotNull Broker broker, @NotNull Environment brokerEnv,
                   @NotNull String brokerUrl, @NotNull String brokerApiKey) {
        this.broker = broker;
        this.brokerEnv = brokerEnv;
        this.brokerUrl = brokerUrl;
        this.brokerApiKey = brokerApiKey;
    }

    public Account(@NotNull Broker broker, @NotNull Environment brokerEnv,
                   @NotNull String brokerUrl, @NotNull String brokerApiKey, String brokerApiSecret) {
        this.broker = broker;
        this.brokerEnv = brokerEnv;
        this.brokerUrl = brokerUrl;
        this.brokerApiKey = brokerApiKey;
        this.brokerApiSecret = brokerApiSecret;
    }

    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        Sid = sid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Broker getBroker() {
        return broker;
    }

    public void setBroker(Broker broker) {
        this.broker = broker;
    }

    public Environment getBrokerEnv() {
        return brokerEnv;
    }

    public void setBrokerEnv(Environment brokerEnv) {
        this.brokerEnv = brokerEnv;
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
