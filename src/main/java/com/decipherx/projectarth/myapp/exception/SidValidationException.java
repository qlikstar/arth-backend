package com.decipherx.projectarth.myapp.exception;

public class SidValidationException extends ValidationException {

    public SidValidationException(String message) {
        super("INVALID SID: " + message);
    }
}
