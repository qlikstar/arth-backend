package com.decipherx.projectarth.scheduler.service;

import com.decipherx.projectarth.scheduler.exception.DataException;
import com.decipherx.projectarth.scheduler.model.AlpacaConnection;
import com.decipherx.projectarth.scheduler.model.Connection;
import com.decipherx.projectarth.webapp.service.UserAccountService;
import com.decipherx.projectarth.webapp.util.Sid;
import io.github.maseev.alpaca.api.AlpacaAPI;
import io.github.maseev.alpaca.api.account.entity.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Singleton
public class SchedulerServiceImpl implements SchedulerService{

    private static final Logger LOG = LoggerFactory.getLogger(SchedulerServiceImpl.class);

    private ConnectionService<Connection, AlpacaAPI> connectionService;
    private ApiDataFetcherService<AlpacaAPI, Account> apiDataFetcherService;
    private UserAccountService userAccountService;

    public SchedulerServiceImpl(UserAccountService userAccountService,
                                ConnectionService<Connection, AlpacaAPI> connectionService,
                                ApiDataFetcherService<AlpacaAPI, Account> apiDataFetcherService) {
        this.userAccountService = userAccountService;
        this.connectionService = connectionService;
        this.apiDataFetcherService = apiDataFetcherService;
    }

    @Override
    public void run() {
        userAccountService.getAllActiveAccounts().stream().map(SchedulerServiceImpl::connectionMapper)
                .forEach( conn -> {
                    LOG.info("connection : " + conn.getAccountSid() );
                    apiDataFetcherService.getAccount(connectionService.getConnection(conn))
                               .exceptionally(ex -> {
                                LOG.error("Exception occurred for Sid {}: {}", conn.getAccountSid(), ex.getMessage());
                                return null;
                            })
                            .thenAccept(acc -> LOG.info("Cash present " + acc.cash()));
        });
    }

    private static AlpacaConnection connectionMapper(com.decipherx.projectarth.webapp.domain.Account account) {

        AlpacaConnection.Builder builder = AlpacaConnection.newBuilder();

        if (account.getBrokerEnv().getEnv().equals("TEST")) {
            builder.type(AlpacaAPI.Type.TEST);
        }
        else if (account.getBrokerEnv().getEnv().equals("LIVE")) {
            builder.type(AlpacaAPI.Type.LIVE);
        } else {
            throw new DataException("Invalid Data found " + account.getBrokerEnv().getEnv());
        }

        return builder.accountSid(new Sid(account.getSid()))
                .apiKey(account.getBrokerApiKey())
                .apiSecret(account.getBrokerApiSecret())
                .build();
    }
}
