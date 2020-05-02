package com.decipherx.projectarth.myapp.enums;

public enum Broker {

    ALPACA("ALPACA"),
    AMERITRADE("AMERITRADE"),
    OANDA("OANDA");

    private final String service;

    Broker(String service) {
        this.service = service;
    }

    public String getService() {
        return this.service;
    }
}
