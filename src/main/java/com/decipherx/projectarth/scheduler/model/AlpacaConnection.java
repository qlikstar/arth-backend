package com.decipherx.projectarth.scheduler.model;

import com.decipherx.projectarth.webapp.util.Sid;
import io.github.maseev.alpaca.api.AlpacaAPI;

public class AlpacaConnection implements Connection {

    private Sid accountSid;
    private AlpacaAPI.Type type ;
    private String apiKey;
    private String apiSecret;

    public Sid getAccountSid() {
        return accountSid;
    }

    public AlpacaAPI.Type getType() {
        return type;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    private AlpacaConnection(Builder builder) {
        accountSid = builder.accountSid;
        type = builder.type;
        apiKey = builder.apiKey;
        apiSecret = builder.apiSecret;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Sid accountSid;
        private AlpacaAPI.Type type;
        private String apiKey;
        private String apiSecret;

        private Builder() {
        }

        public Builder accountSid(Sid val) {
            accountSid = val;
            return this;
        }

        public Builder type(AlpacaAPI.Type val) {
            type = val;
            return this;
        }

        public Builder apiKey(String val) {
            apiKey = val;
            return this;
        }

        public Builder apiSecret(String val) {
            apiSecret = val;
            return this;
        }

        public AlpacaConnection build() {
            return new AlpacaConnection(this);
        }
    }
}
