package com.decipherx.projectarth.scopes;

import io.micronaut.runtime.context.scope.ThreadLocal;

@ThreadLocal
public class MiddleService {

    private String correlationId;

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

}
