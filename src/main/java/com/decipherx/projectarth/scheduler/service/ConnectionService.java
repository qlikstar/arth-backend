package com.decipherx.projectarth.scheduler.service;

import com.decipherx.projectarth.scheduler.model.Connection;

public interface ConnectionService<T extends Connection, X> {

    X getConnection(T connection);
}
