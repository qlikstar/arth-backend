package com.decipherx.projectarth.scheduler.service;

import java.util.concurrent.CompletableFuture;

public interface ApiDataFetcherService<I,O> {

    CompletableFuture<O> getAccount(I input);

}
