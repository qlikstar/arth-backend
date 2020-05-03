package com.decipherx.projectarth.webapp.exception;

public class SidValidationException extends ValidationException {

    public SidValidationException(String message) {
        super("INVALID SID: " + message);
    }
}
