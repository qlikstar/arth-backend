package com.decipherx.projectarth.webapp.enums;

public enum Environment {

    TEST("TEST"),
    LIVE("LIVE");

    private final String env;

    Environment(String env) {
        this.env = env;
    }

    public String getEnv() {
        return this.env;
    }
}