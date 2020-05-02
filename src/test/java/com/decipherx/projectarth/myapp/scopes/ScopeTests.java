package com.decipherx.projectarth.myapp.scopes;

import com.decipherx.projectarth.scopes.BeginService;
import com.decipherx.projectarth.scopes.FinishService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import javax.inject.Inject;

import io.micronaut.test.annotation.MicronautTest;

@MicronautTest
public class ScopeTests {

    @Inject
    BeginService beginService;

    @Inject
    FinishService finishService;

    @Test
    public void testThreadLocalScope() {
        final Random r = new Random();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        IntStream.range(0,20).forEach(i -> {
            executor.execute(() -> {
                String correlationId = "abc" + r.nextInt(10000);
                beginService.start(correlationId);
                Assertions.assertEquals(correlationId, finishService.finish());
                System.out.println("Correlation ID" + correlationId);
            });
        });
    }
}
