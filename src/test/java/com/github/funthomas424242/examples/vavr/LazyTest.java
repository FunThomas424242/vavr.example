package com.github.funthomas424242.examples.vavr;

import io.vavr.Lazy;
import io.vavr.concurrent.Future;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Aufgabe der Lazy Klasse ist es, Kode für eine spätere Ausführung zu speichern.
 * Der Kode wird erst ausgeführt wenn jemand versucht auf das Ergebnis zuzugreifen.
 * Vorteil:
 * (1) Interessiert sich nie einer für das Ergebnis -> wird der Kode nie ausgeführt.
 * (2) Ich kann erst mal weiter arbeiten und die Ausführung einer bestimmten Berechnung (z.B. Langläufer)
 * auf später verschieben.
 */
public class LazyTest {

    @Test
    @DisplayName("Synchrones Lazy mit schneller Berechnung")
    public void syncLazyCall() {

        final Lazy<Double> lazy = Lazy.of(Math::random);
        assertFalse(lazy.isEvaluated());

        final double val1 = lazy.get();
        assertTrue(lazy.isEvaluated());

        final double val2 = lazy.get();
        assertEquals(val1, val2, 0.1);

    }

    @Test
    @DisplayName("Synchrones Lazy mit lang laufender Berechnung")
    public void asyncLazyCall() {

        System.out.println("Definiere die Berechnung als Lazy");
        final Lazy<Double> lazy = Lazy.of(() -> {
            double count = 0;
            for (int i = 0; i < 20; i++) {
                System.out.println("i=" + i);
                count = (double) i;
            }
            System.out.println("Beende Berechnung");
            return count;
        });
        System.out.println("Prüfe auf Ende der  Berechnung");
        assertFalse(lazy.isEvaluated());

        System.out.println("Frage Ergebnis der Berechnung ab und starte damit die Ausführung der Berechnung");
        final double val1 = lazy.get();
        assertTrue(lazy.isEvaluated());

        final double val2 = lazy.get();
        assertEquals(val1, val2);

        assertEquals(19, val1);
        assertEquals(19, val2);
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

}
