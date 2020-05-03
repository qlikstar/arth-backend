package com.decipherx.projectarth.scheduler;

import io.github.maseev.alpaca.http.exception.APIException;
import io.github.maseev.alpaca.http.transformer.ValueTransformer;
import io.github.maseev.alpaca.v1.AlpacaAPI;
import io.github.maseev.alpaca.v1.account.entity.Account;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import static io.github.maseev.alpaca.v1.AlpacaAPI.Type.TEST;
import static io.github.maseev.alpaca.v1.AlpacaAPI.Version.V1;

public class Test {

    public static void main(String[] args) {

        String keyId = "PK8424PA3DFWTD7FSXST";
        String secretKey = "I/emuHUCdB6z0yGuUlP6ITP6mauglNOak6VzP8UE";

        AlpacaAPI api = new AlpacaAPI(TEST, V1, keyId, secretKey);
        CompletableFuture<Account> account = api.account().get().thenApply(x -> {
            try {
                return new ValueTransformer<>(Account.class).transform(x.getResponseBody());
            } catch (APIException | IOException e) {
                e.printStackTrace();
            }
            return null;
        });

        account.thenAccept( x -> System.out.println(x.patternDayTrader()));
    }


}
