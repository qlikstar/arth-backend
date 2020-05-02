package com.decipherx.projectarth.myapp.exception;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {ValidationException.class, ExceptionHandler.class})
public class ValidationExceptionHandler implements ExceptionHandler<ValidationException, HttpResponse<String>> {

    @Override
    public HttpResponse<String> handle(HttpRequest request, ValidationException exception) {
        return HttpResponse.badRequest(exception.getMessage());
    }
}
