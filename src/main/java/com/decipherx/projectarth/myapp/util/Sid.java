package com.decipherx.projectarth.myapp.util;

import com.decipherx.projectarth.myapp.exception.ExceptionMessages;
import com.decipherx.projectarth.myapp.exception.SidValidationException;
import com.decipherx.projectarth.myapp.exception.ValidationException;
import java.util.UUID;

public class Sid {

    private String result;

    public Sid(String result) {
        this.result = result;
    }

    public String getValue(){
        return this.result;
    }

    public static Sid generateSid(String prefix) {
        return new Sid(String.format("%s-%s", prefix, UUID.randomUUID().toString().toUpperCase()));
    }

    public static void isValidSid(String couldBeSid) throws ValidationException {
        if (couldBeSid.length() != 40) {
            throw new SidValidationException(String.format(ExceptionMessages.SID_LENGTH_ERROR_MSG, couldBeSid));
        } else {

            String[] splits = couldBeSid.split("-", 2);
            if (splits.length != 2)
                throw new SidValidationException(String.format(ExceptionMessages.INVALID_SID_FORMAT_ERROR_MSG, couldBeSid));

            if (!SidPrefix.ALL_PREFIXES.contains(splits[0]))
                throw  new SidValidationException(String.format(ExceptionMessages.INVALID_SID_TYPE_ERROR_MSG, splits[0]));

            try {
                UUID ignored = UUID.fromString(splits[1]);
            } catch (Exception ex) {
                throw new SidValidationException(String.format(ExceptionMessages.INVALID_UUID_IN_SID_ERROR_MSG, splits[1]));
            }

        }
    }

    public static Sid convertToSid(String couldBeSid) throws ValidationException {
        isValidSid(couldBeSid);
        return new Sid(couldBeSid);
    }
}