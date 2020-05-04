package com.decipherx.projectarth.scheduler.service;

import io.github.maseev.alpaca.api.AlpacaAPI;
import io.github.maseev.alpaca.api.account.entity.Account;

import javax.inject.Singleton;
import java.util.concurrent.CompletableFuture;

@Singleton
public class AlpacaDataFetcherServiceImpl implements ApiDataFetcherService<AlpacaAPI, Account> {

    @Override
    public CompletableFuture<Account> getAccount(AlpacaAPI alpacaAPI) {
        return alpacaAPI.account().get();
    }
}
