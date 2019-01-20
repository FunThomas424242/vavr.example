package com.github.funthomas424242.examples.vavr;

import io.vavr.concurrent.Future;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.Executors.newSingleThreadExecutor;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Ein Future in Vavr entspricht einem Promise in Javascript
 * oder aber ein CompletableFuture in Java.
 * Ein Vavr Future unterstützt aber die objektfunktionale Denke im Gegensatz
 * zum Java CompletableFuture.
 */
public class FutureTest {

    @Test
    @DisplayName("Asynchrone Abarbeitung in einem Thread")
    public void whenChangeExecutorService_thenCorrect() {
        final String result = Future.of(newSingleThreadExecutor(), () -> "Hello")
                .getOrElse("Fehler");
        assertEquals("Hello", result);
    }

    @Test
    @DisplayName("Division durch 2 -> success = 5")
    public void whenDivideByZero_thenGetValue() {
        Future<Integer> resultFuture = Future.of(() -> 10 / 2)
                .onSuccess(v -> assertEquals(5, v.intValue()))
                .onFailure(v -> fail());
        assertEquals((Integer) 5, resultFuture.get());
    }

    @Test
    @DisplayName("Division durch 0 -> Message / by zero")
    public void whenDivideByZero_thenGetThrowable2() {
        Future<Integer> resultFuture = Future.of(() -> 10 / 0)
                .await();

        assertEquals(resultFuture.getCause().get().getMessage(),
                ("/ by zero"));
    }

    @Test
    @DisplayName("Division durch 0 -> ArithmethicException")
    public void whenDivideByZero_thenGetThrowable1() {
        Future<Integer> resultFuture = Future.of(() -> 10 / 0);

        assertThrows(ArithmeticException.class, resultFuture::get);
    }


    @Test
    @DisplayName("Future Berechnung endet nach Ausführung.")
    public void whenDivideByZero_thenCorrect() {
        Future<Integer> resultFuture = Future.of(() -> 10 / 0)
                .await();

        assertTrue(resultFuture.isCompleted());
        assertFalse(resultFuture.isSuccess());
        assertTrue(resultFuture.isFailure());
    }

    @Test
    public void whenCallMap_thenCorrect() {
        Future<String> futureResult = Future.of(() -> "from Baeldung")
                .map(a -> "Hello " + a)
                .await();

        assertEquals(futureResult.get(), "Hello from Baeldung");
    }

    @Test
    public void whenCallFlatMap_thenCorrect() {
        Future<Object> futureMap = Future.of(() -> 1)
                .flatMap((i) -> Future.of(() -> "Hello: " + i));

        assertEquals(futureMap.get(), "Hello: 1");
    }

    @Test
    public void whenConvertToCompletableFuture_thenCorrect()
            throws Exception {

        CompletableFuture<String> convertedFuture = Future.of(() -> "Hello: ")
                .toCompletableFuture();

        assertEquals(convertedFuture.get(), "Hello: ");
    }

}
