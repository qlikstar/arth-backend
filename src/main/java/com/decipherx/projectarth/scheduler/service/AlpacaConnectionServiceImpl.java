package com.decipherx.projectarth.scheduler.service;

import com.decipherx.projectarth.scheduler.model.AlpacaConnection;
import io.github.maseev.alpaca.api.AlpacaAPI;

import javax.inject.Singleton;

import static io.github.maseev.alpaca.api.AlpacaAPI.Version.V2;

@Singleton
public class AlpacaConnectionServiceImpl implements AlpacaConnectionService {

    @Override
    public AlpacaAPI getConnection(AlpacaConnection connection) {
        return new AlpacaAPI(connection.getType(), V2, connection.getApiKey(), connection.getApiSecret());
    }

}
