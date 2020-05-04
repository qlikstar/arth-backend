package com.decipherx.projectarth.scheduler;

import io.github.maseev.alpaca.api.AlpacaAPI;
import io.github.maseev.alpaca.api.account.entity.Account;

import java.util.concurrent.CompletableFuture;

import static io.github.maseev.alpaca.api.AlpacaAPI.Type.TEST;
import static io.github.maseev.alpaca.api.AlpacaAPI.Version.V2;

public class Test {

    public static void main(String[] args) {

        String keyId = "PK8424PA3DFWTD7FSXST";
        String secretKey = "I/emuHUCdB6z0yGuUlP6ITP6mauglNOak6VzP8UE";

        AlpacaAPI api = new AlpacaAPI(TEST, V2, keyId, secretKey);
        CompletableFuture<Account> account = api.account().get();
        account.thenAccept(acc -> System.out.println("Account number : " + acc.accountNumber()))
                .exceptionally(ex -> {
                    System.out.println(ex.getLocalizedMessage());
                    return null;
                });
    }

}
